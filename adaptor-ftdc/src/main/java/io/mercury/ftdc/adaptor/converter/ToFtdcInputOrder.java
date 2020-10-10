package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.ftdc.adaptor.consts.FtdcContingentCondition;
import io.mercury.ftdc.adaptor.consts.FtdcDirection;
import io.mercury.ftdc.adaptor.consts.FtdcForceCloseReason;
import io.mercury.ftdc.adaptor.consts.FtdcHedgeFlag;
import io.mercury.ftdc.adaptor.consts.FtdcOffsetFlag;
import io.mercury.ftdc.adaptor.consts.FtdcOrderPriceType;
import io.mercury.ftdc.adaptor.consts.FtdcTimeCondition;
import io.mercury.ftdc.adaptor.consts.FtdcVolumeCondition;
import io.mercury.redstone.core.order.ActualChildOrder;
import io.mercury.redstone.core.order.Order;

public final class ToFtdcInputOrder implements Function<Order, CThostFtdcInputOrderField> {

	@Override
	public CThostFtdcInputOrderField apply(Order order) {
		ActualChildOrder childOrder = (ActualChildOrder) order;
		Instrument instrument = order.instrument();
		CThostFtdcInputOrderField inputOrderField = new CThostFtdcInputOrderField();
		/**
		 * 设置交易所ID
		 */
		inputOrderField.setExchangeID(instrument.symbol().exchange().code());
		/**
		 * 设置交易标的
		 */
		inputOrderField.setInstrumentID(instrument.code());
		/**
		 * 设置报单价格
		 */
		inputOrderField.setOrderPriceType(FtdcOrderPriceType.LimitPrice);
		/**
		 * 设置开平标识
		 */
		switch (childOrder.action()) {
		case Open:
			inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.OpenStr);
			break;
		case Close:
			inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.CloseStr);
			break;
		case CloseToday:
			inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.CloseTodayStr);
			break;
		case CloseYesterday:
			inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.CloseYesterdayStr);
			break;
		default:
			throw new IllegalStateException(childOrder.action() + " is invalid");
		}
		/**
		 * 设置投机标识
		 */
		inputOrderField.setCombHedgeFlag(FtdcHedgeFlag.SpeculationStr);
		/**
		 * 设置买卖方向
		 */
		switch (order.direction()) {
		case Long:
			inputOrderField.setDirection(FtdcDirection.Buy);
			break;
		case Short:
			inputOrderField.setDirection(FtdcDirection.Sell);
			break;
		case Invalid:
			throw new IllegalStateException(order.direction() + " is Invalid.");
		}
		/**
		 * 设置价格
		 */
		PriceMultiplier multiplier = instrument.symbol().getPriceMultiplier();
		inputOrderField.setLimitPrice(multiplier.toDouble(order.price().offerPrice()));
		/**
		 * 设置数量
		 */
		inputOrderField.setVolumeTotalOriginal(order.qty().offerQty());
		/**
		 * 设置有效期类型
		 */
		inputOrderField.setTimeCondition(FtdcTimeCondition.GFD);
		/**
		 * 设置成交量类型
		 */
		inputOrderField.setVolumeCondition(FtdcVolumeCondition.AV);
		/**
		 * 设置最小成交数量
		 */
		inputOrderField.setMinVolume(1);
		/**
		 * 设置触发条件
		 */
		inputOrderField.setContingentCondition(FtdcContingentCondition.Immediately);
		/**
		 * 设置止损价格
		 */
		inputOrderField.setStopPrice(0.0D);
		/**
		 * 设置强平原因: 此处固定为非强平
		 */
		inputOrderField.setForceCloseReason(FtdcForceCloseReason.NotForceClose);
		/**
		 * 设置自动挂起标识
		 */
		inputOrderField.setIsAutoSuspend(0);
		/**
		 * 返回FTDC新订单对象
		 */
		return inputOrderField;
	}

}
