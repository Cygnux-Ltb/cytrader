package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInvestorPositionField;
import io.mercury.ftdc.gateway.bean.FtdcInvestorPosition;

public class FromCThostFtdcInvestorPosition implements Function<CThostFtdcInvestorPositionField, FtdcInvestorPosition> {

	@Override
	public FtdcInvestorPosition apply(CThostFtdcInvestorPositionField from) {
		return new FtdcInvestorPosition()
				/// 合约代码
				.setInstrumentID(from.getInstrumentID())

				/// 经纪公司代码
				.setBrokerID(from.getBrokerID())

				/// 投资者代码
				.setInvestorID(from.getInvestorID())

				/// 持仓多空方向
				.setPosiDirection(from.getPosiDirection())

				/// 投机套保标志
				.setHedgeFlag(from.getHedgeFlag())

				/// 持仓日期
				.setPositionDate(from.getPositionDate())

				/// 上日持仓
				.setYdPosition(from.getYdPosition())

				/// 今日持仓
				.setPosition(from.getPosition())

				/// 多头冻结
				.setLongFrozen(from.getLongFrozen())

				/// 空头冻结
				.setShortFrozen(from.getShortFrozen())

				/// 开仓冻结金额
				.setLongFrozenAmount(from.getLongFrozenAmount())

				/// 开仓冻结金额
				.setShortFrozenAmount(from.getShortFrozenAmount())

				/// 开仓量
				.setOpenVolume(from.getOpenVolume())

				/// 平仓量
				.setCloseVolume(from.getCloseVolume())

				/// 开仓金额
				.setOpenAmount(from.getOpenAmount())

				/// 平仓金额
				.setCloseAmount(from.getCloseAmount())

				/// 持仓成本
				.setPositionCost(from.getPositionCost())

				/// 上次占用的保证金
				.setPreMargin(from.getPreMargin())

				/// 占用的保证金
				.setUseMargin(from.getUseMargin())

				/// 冻结的保证金
				.setFrozenMargin(from.getFrozenMargin())

				/// 冻结的资金
				.setFrozenCash(from.getFrozenCash())

				/// 冻结的手续费
				.setFrozenCommission(from.getFrozenCommission())

				/// 资金差额
				.setCashIn(from.getCashIn())

				/// 手续费
				.setCommission(from.getCommission())

				/// 平仓盈亏
				.setCloseProfit(from.getCloseProfit())

				/// 持仓盈亏
				.setPositionProfit(from.getPositionProfit())

				/// 上次结算价
				.setPreSettlementPrice(from.getPreSettlementPrice())

				/// 本次结算价
				.setSettlementPrice(from.getSettlementPrice())

				/// 交易日
				.setTradingDay(from.getTradingDay())

				/// 结算编号
				.setSettlementID(from.getSettlementID())

				/// 开仓成本
				.setOpenCost(from.getOpenCost())

				/// 交易所保证金
				.setExchangeMargin(from.getExchangeMargin())

				/// 组合成交形成的持仓
				.setCombPosition(from.getCombPosition())

				/// 组合多头冻结
				.setCombLongFrozen(from.getCombLongFrozen())

				/// 组合空头冻结
				.setCombShortFrozen(from.getCombShortFrozen())

				/// 逐日盯市平仓盈亏
				.setCloseProfitByDate(from.getCloseProfitByDate())

				/// 逐笔对冲平仓盈亏
				.setCloseProfitByTrade(from.getCloseProfitByTrade())

				/// 今日持仓
				.setTodayPosition(from.getTodayPosition())

				/// 保证金率
				.setMarginRateByMoney(from.getMarginRateByMoney())

				/// 保证金率(按手数)
				.setMarginRateByVolume(from.getMarginRateByVolume())

				/// 执行冻结
				.setStrikeFrozen(from.getStrikeFrozen())

				/// 执行冻结金额
				.setStrikeFrozenAmount(from.getStrikeFrozenAmount())

				/// 放弃执行冻结
				.setAbandonFrozen(from.getAbandonFrozen())

				/// 交易所代码
				.setExchangeID(from.getExchangeID())

				/// 执行冻结的昨仓
				.setYdStrikeFrozen(from.getYdStrikeFrozen())

				/// 投资单元代码
				.setInvestUnitID(from.getInvestUnitID())

				/// 大商所持仓成本差值，只有大商所使用
				.setPositionCostOffset(from.getPositionCostOffset());
	}

}
