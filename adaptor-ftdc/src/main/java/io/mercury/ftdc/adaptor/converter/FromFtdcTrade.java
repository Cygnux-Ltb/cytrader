package io.mercury.ftdc.adaptor.converter;

import static io.mercury.common.util.StringUtil.delNonNumeric;

import java.util.function.Function;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.ftdc.adaptor.FtdcConstMapper;
import io.mercury.ftdc.adaptor.OrderRefKeeper;
import io.mercury.ftdc.gateway.bean.FtdcTrade;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdReport;

public final class FromFtdcTrade implements Function<FtdcTrade, OrdReport> {

	@Override
	public OrdReport apply(FtdcTrade ftdcTrade) {
		String orderRef = ftdcTrade.getOrderRef();
		long uniqueId = OrderRefKeeper.getUniqueId(orderRef);
		OrdReport report = new OrdReport(uniqueId);
		/**
		 * 投资者ID
		 */
		report.setInvestorId(ftdcTrade.getInvestorID());
		/**
		 * 报单引用
		 */
		report.setOrderRef(orderRef);
		/**
		 * 时间戳
		 */
		report.setEpochMillis(System.currentTimeMillis());
		/**
		 * 报单编号
		 */
		report.setBrokerUniqueId(ftdcTrade.getOrderSysID());
		/**
		 * 合约代码
		 */
		Instrument instrument = InstrumentManager.getInstrument(ftdcTrade.getInstrumentID());
		report.setInstrument(instrument);
		/**
		 * 报单状态
		 */
		report.setOrdStatus(OrdStatus.Unprovided);
		/**
		 * 买卖方向
		 */
		TrdDirection direction = FtdcConstMapper.fromDirection(ftdcTrade.getDirection());
		report.setDirection(direction);
		/**
		 * 组合开平标志
		 */
		TrdAction action = FtdcConstMapper.fromOffsetFlag(ftdcTrade.getOffsetFlag());
		report.setAction(action);
		/**
		 * 完成数量
		 */
		report.setFilledQty(ftdcTrade.getVolume());
		/**
		 * 成交价格
		 */
		PriceMultiplier multiplier = instrument.symbol().getPriceMultiplier();
		report.setTradePrice(multiplier.toLong(ftdcTrade.getPrice()));
		/**
		 * 最后修改时间
		 */
		report.setLastUpdateTime(delNonNumeric(ftdcTrade.getTradeDate()) + delNonNumeric(ftdcTrade.getTradeTime()));
		return report;
	}

}
