package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.financial.instrument.Instrument;
import io.mercury.ftdc.adaptor.FtdcConst;
import io.mercury.ftdc.adaptor.consts.ContingentConditionConst;
import io.mercury.ftdc.adaptor.consts.DirectionConst;
import io.mercury.ftdc.adaptor.consts.ForceCloseReason;
import io.mercury.ftdc.adaptor.consts.OrderPriceTypeConst;
import io.mercury.ftdc.adaptor.consts.VolumeConditionConst;
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

		ftdcInputOrder.setOrderPriceType(OrderPriceTypeConst.LimitPrice);

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
			ftdcInputOrder.setDirection(DirectionConst.Buy);
			break;
		case Short:
			ftdcInputOrder.setDirection(DirectionConst.Sell);
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

		/**
		 * 
		 */
		ftdcInputOrder.setTimeCondition(OrderPriceTypeConst.LimitPrice);
		
		/**
		 * 
		 */
		ftdcInputOrder.setVolumeCondition(VolumeConditionConst.AV);
		/**
		 * 设置最小成交数量
		 */
		ftdcInputOrder.setMinVolume(1);
		
		/**
		 * 设置触发条件
		 */
		ftdcInputOrder.setContingentCondition(ContingentConditionConst.Immediately);
		/**
		 * 设置止损价格
		 */
		ftdcInputOrder.setStopPrice(0.0D);
		/**
		 * 
		 */
		ftdcInputOrder.setForceCloseReason(ForceCloseReason.NotForceClose);
		/**
		 * 设置自动挂起标识
		 */
		ftdcInputOrder.setIsAutoSuspend(0);
		return ftdcInputOrder;
	}

}
