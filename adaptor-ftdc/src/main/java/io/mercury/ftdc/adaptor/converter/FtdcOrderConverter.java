package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import io.mercury.common.util.StringUtil;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.ftdc.adaptor.FtdcConstMapper;
import io.mercury.ftdc.adaptor.OrderRefKeeper;
import io.mercury.ftdc.gateway.bean.FtdcOrder;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
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

		// 合约代码
		Instrument instrument = InstrumentManager.getInstrument(ftdcOrder.getInstrumentID());
		report.setInstrument(instrument);
		// 报单状态
		OrdStatus ordStatus = FtdcConstMapper.fromOrderStatus(ftdcOrder.getOrderStatus());
		report.setOrdStatus(ordStatus);

		// 买卖方向
		TrdDirection direction = FtdcConstMapper.fromDirection(ftdcOrder.getDirection());
		report.setDirection(direction);

		// 组合开平标志
		TrdAction action = FtdcConstMapper.fromCombOffsetFlag(ftdcOrder.getCombOffsetFlag());
		report.setAction(action);

		// 委托数量
		report.setOfferQty(ftdcOrder.getVolumeTotalOriginal());
		// 完成数量
		report.setFilledQty(ftdcOrder.getVolumeTraded());

		// 委托价格
		PriceMultiplier multiplier = instrument.symbol().priceMultiplier();
		report.setOfferPrice(multiplier.convertToLong(ftdcOrder.getLimitPrice()));

		// 报单日期 + 委托时间
		report.setOfferTime(StringUtil.delNonNumeric(ftdcOrder.getInsertDate())
				+ StringUtil.delNonNumeric(ftdcOrder.getInsertTime()));

		// 最后修改时间
		ftdcOrder.getUpdateTime();

		return report;
	}

}
