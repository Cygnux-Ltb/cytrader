package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import io.mercury.ftdc.adaptor.OrderRefKeeper;
import io.mercury.ftdc.gateway.bean.FtdcTrade;
import io.mercury.redstone.core.order.structure.OrdReport;

public final class FtdcTradeConverter implements Function<FtdcTrade, OrdReport> {

	@Override
	public OrdReport apply(FtdcTrade ftdcTrade) {
		String orderRef = ftdcTrade.getOrderRef();
		long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
		OrdReport report = new OrdReport(ordSysId);

		// 经纪公司代码
		ftdcTrade.getBrokerID();
		// 投资者代码
		ftdcTrade.getInvestorID();
		// 合约代码
		ftdcTrade.getInstrumentID();
		// 报单引用
		ftdcTrade.getOrderRef();
		// 用户代码
		ftdcTrade.getUserID();
		// 交易所代码
		ftdcTrade.getExchangeID();
		// 成交编号
		ftdcTrade.getTradeID();
		// 买卖方向
		ftdcTrade.getDirection();
		// 报单编号
		ftdcTrade.getOrderSysID();
		// 会员代码
		ftdcTrade.getParticipantID();
		// 客户代码
		ftdcTrade.getClientID();
		// 交易角色
		ftdcTrade.getTradingRole();
		// 合约在交易所的代码
		ftdcTrade.getExchangeInstID();
		// 开平标志
		ftdcTrade.getOffsetFlag();
		// 投机套保标志
		ftdcTrade.getHedgeFlag();
		// 价格
		ftdcTrade.getPrice();
		// 数量
		ftdcTrade.getVolume();
		// 成交时期
		ftdcTrade.getTradeDate();
		// 成交时间
		ftdcTrade.getTradeTime();
		// 成交类型
		ftdcTrade.getTradeType();
		// 成交价来源
		ftdcTrade.getPriceSource();
		// 交易所交易员代码
		ftdcTrade.getTraderID();
		// 本地报单编号
		ftdcTrade.getOrderLocalID();
		// 结算会员编号
		ftdcTrade.getClearingPartID();
		// 业务单元
		ftdcTrade.getBusinessUnit();
		// 序号
		ftdcTrade.getSequenceNo();
		// 交易日
		ftdcTrade.getTradingDay();
		// 结算编号
		ftdcTrade.getSettlementID();
		// 经纪公司报单编号
		ftdcTrade.getBrokerOrderSeq();
		// 成交来源
		ftdcTrade.getTradeSource();
		// 投资单元代码
		ftdcTrade.getInvestUnitID();

		return report;
	}

}
