package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentKeeper;
import io.mercury.ftdc.adaptor.OrdStatusMapper;
import io.mercury.ftdc.adaptor.OrderRefKeeper;
import io.mercury.ftdc.gateway.bean.FtdcOrder;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.structure.OrdReport;

public final class FtdcOrderConverter implements Function<FtdcOrder, OrdReport> {

	@Override
	public OrdReport apply(FtdcOrder ftdcOrder) {
		String orderRef = ftdcOrder.getOrderRef();
		long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
		OrdReport report = new OrdReport(ordSysId);
		// 报单引用
		report.setOrderRef(orderRef);
		// 时间戳
		report.setEpochMillis(System.currentTimeMillis());
		// 报单编号
		report.setBrokerUniqueId(ftdcOrder.getOrderSysID());
		// 委托数量
		report.setOfferQty(ftdcOrder.getVolumeTotalOriginal());
		// 完成数量
		report.setFilledQty(ftdcOrder.getVolumeTraded());
		// 合约代码
		Instrument instrument = InstrumentKeeper.getInstrument(ftdcOrder.getInstrumentID());
		report.setInstrument(instrument);
		// 报单状态
		OrdStatus ordStatus = OrdStatusMapper.ofFtdcOrderStatus(ftdcOrder.getOrderStatus());
		report.setOrdStatus(ordStatus);

		// 组合开平标志
		ftdcOrder.getCombOffsetFlag();

		// 买卖方向
		ftdcOrder.getDirection();

		// 价格
		ftdcOrder.getLimitPrice();

		// 报单日期
		ftdcOrder.getInsertDate();
		// 委托时间
		ftdcOrder.getInsertTime();

		// 最后修改时间
		ftdcOrder.getUpdateTime();

		return report;
	}

}
