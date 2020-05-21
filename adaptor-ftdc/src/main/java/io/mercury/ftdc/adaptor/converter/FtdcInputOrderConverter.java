package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.financial.instrument.Instrument;
import io.mercury.ftdc.adaptor.FtdcConst;
import io.mercury.ftdc.adaptor.consts.FtdcDirectionConst;
import io.mercury.ftdc.adaptor.consts.FtdcOrderPriceTypeConst;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.specific.ChildOrder;

public final class FtdcInputOrderConverter implements Function<Order, CThostFtdcInputOrderField> {

	@Override
	public CThostFtdcInputOrderField apply(Order order) {
		ChildOrder childOrder = (ChildOrder) order;
		Instrument instrument = order.instrument();
		CThostFtdcInputOrderField ftdcInputOrder = new CThostFtdcInputOrderField();
		/**
		 * 设置交易所ID
		 */
		ftdcInputOrder.setExchangeID(instrument.symbol().exchange().code());
		/**
		 * 设置交易标的
		 */
		ftdcInputOrder.setInstrumentID(instrument.code());

		ftdcInputOrder.setOrderPriceType(FtdcOrderPriceTypeConst.LimitPrice);

		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcOffsetFlagType是一个开平标志类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///开仓<br>
		 * #define THOST_FTDC_OF_Open '0' <br>
		 * ///平仓<br>
		 * #define THOST_FTDC_OF_Close '1'<br>
		 * ///强平<br>
		 * #define THOST_FTDC_OF_ForceClose '2' <br>
		 * ///平今<br>
		 * #define THOST_FTDC_OF_CloseToday '3'<br>
		 * ///平昨<br>
		 * #define THOST_FTDC_OF_CloseYesterday '4' <br>
		 * ///强减<br>
		 * #define THOST_FTDC_OF_ForceOff '5'<br>
		 * ///本地强平<br>
		 * #define THOST_FTDC_OF_LocalForceClose '6'
		 */
		switch (childOrder.action()) {
		case Open:
			ftdcInputOrder.setCombOffsetFlag(FtdcConst.CombOffsetFlagOpenStr);
			break;
		case Close:
			ftdcInputOrder.setCombOffsetFlag(FtdcConst.CombOffsetFlagCloseStr);
			break;
		case CloseToday:
			ftdcInputOrder.setCombOffsetFlag(FtdcConst.CombOffsetFlagCloseTodayStr);
			break;
		case CloseYesterday:
			ftdcInputOrder.setCombOffsetFlag(FtdcConst.CombOffsetFlagCloseYesterdayStr);
			break;
		default:
			throw new RuntimeException(childOrder.action() + " does not exist.");
		}

		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcHedgeFlagType是一个投机套保标志类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///投机<br>
		 * #define THOST_FTDC_HF_Speculation '1'<br>
		 * ///套利<br>
		 * #define THOST_FTDC_HF_Arbitrage '2'<br>
		 * ///套保<br>
		 * #define THOST_FTDC_HF_Hedge '3'<br>
		 * ///做市商<br>
		 * #define THOST_FTDC_HF_MarketMaker '5'<br>
		 * ///第一腿投机第二腿套保 大商所专用<br>
		 * #define THOST_FTDC_HF_SpecHedge '6'<br>
		 * ///第一腿套保第二腿投机 大商所专用<br>
		 * #define THOST_FTDC_HF_HedgeSpec '7'<br>
		 */
		ftdcInputOrder.setCombHedgeFlag(FtdcConst.CombHedgeFlagSpeculationStr);

		switch (order.direction()) {
		case Long:
			ftdcInputOrder.setDirection(FtdcDirectionConst.Buy);
			break;
		case Short:
			ftdcInputOrder.setDirection(FtdcDirectionConst.Sell);
			break;
		case Invalid:
			throw new RuntimeException(order.direction() + " is Invalid.");
		}
		/**
		 * 设置价格
		 */
		ftdcInputOrder.setLimitPrice(order.ordPrice().offerPrice());
		/**
		 * 设置数量
		 */
		ftdcInputOrder.setVolumeTotalOriginal(order.ordQty().offerQty());

		ftdcInputOrder.setTimeCondition(FtdcOrderPriceTypeConst.LimitPrice);
		/**
		 * /////////////////////////////////////////////////////////////////////////<br>
		 * ///TFtdcVolumeConditionType是一个成交量类型类型<br>
		 * /////////////////////////////////////////////////////////////////////////<br>
		 * ///任何数量<br>
		 * #define THOST_FTDC_VC_AV '1'<br>
		 * ///最小数量<br>
		 * #define THOST_FTDC_VC_MV '2'<br>
		 * ///全部数量<br>
		 * #define THOST_FTDC_VC_CV '3'<br>
		 */
		ftdcInputOrder.setVolumeCondition(thosttraderapiConstants.THOST_FTDC_VC_AV);
		/**
		 * 设置最小成交数量
		 */
		ftdcInputOrder.setMinVolume(1);
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcContingentConditionType是一个触发条件类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///立即<br>
		 * #define THOST_FTDC_CC_Immediately '1'<br>
		 * ///止损<br>
		 * #define THOST_FTDC_CC_Touch '2'<br>
		 * ///止赢<br>
		 * #define THOST_FTDC_CC_TouchProfit '3'<br>
		 * ///预埋单<br>
		 * #define THOST_FTDC_CC_ParkedOrder '4'<br>
		 * ///最新价大于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceGreaterThanStopPrice '5'<br>
		 * ///最新价大于等于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceGreaterEqualStopPrice '6'<br>
		 * ///最新价小于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceLesserThanStopPrice '7'<br>
		 * ///最新价小于等于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceLesserEqualStopPrice '8'<br>
		 * ///卖一价大于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceGreaterThanStopPrice '9'<br>
		 * ///卖一价大于等于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceGreaterEqualStopPrice 'A'<br>
		 * ///卖一价小于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceLesserThanStopPrice 'B'<br>
		 * ///卖一价小于等于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceLesserEqualStopPrice 'C'<br>
		 * ///买一价大于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceGreaterThanStopPrice 'D'<br>
		 * ///买一价大于等于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceGreaterEqualStopPrice 'E'<br>
		 * ///买一价小于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceLesserThanStopPrice 'F'<br>
		 * ///买一价小于等于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceLesserEqualStopPrice 'H'<br>
		 */
		ftdcInputOrder.setContingentCondition(thosttraderapiConstants.THOST_FTDC_CC_Immediately);
		/**
		 * 设置止损价格
		 */
		ftdcInputOrder.setStopPrice(0.0D);
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcForceCloseReasonType是一个强平原因类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///非强平<br>
		 * #define THOST_FTDC_FCC_NotForceClose '0'<br>
		 * ///资金不足<br>
		 * #define THOST_FTDC_FCC_LackDeposit '1'<br>
		 * ///客户超仓<br>
		 * #define THOST_FTDC_FCC_ClientOverPositionLimit '2'<br>
		 * ///会员超仓<br>
		 * #define THOST_FTDC_FCC_MemberOverPositionLimit '3'<br>
		 * ///持仓非整数倍<br>
		 * #define THOST_FTDC_FCC_NotMultiple '4'<br>
		 * ///违规<br>
		 * #define THOST_FTDC_FCC_Violation '5'<br>
		 * ///其它<br>
		 * #define THOST_FTDC_FCC_Other '6'<br>
		 * ///自然人临近交割<br>
		 * #define THOST_FTDC_FCC_PersonDeliv '7'<br>
		 */
		ftdcInputOrder.setForceCloseReason(thosttraderapiConstants.THOST_FTDC_FCC_NotForceClose);
		/**
		 * 设置自动挂起标识
		 */
		ftdcInputOrder.setIsAutoSuspend(0);
		return ftdcInputOrder;
	}

}
