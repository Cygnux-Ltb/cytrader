package io.ffreedom.redstone.strategy.impl.straight.chart;

import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.indicators.impl.candle.Candle;
import io.ffreedom.indicators.impl.ma.SMA;

public class MA180ShortTermChart   {

	private SMA sma5 = new SMA(5);
	private SMA sma10 = new SMA(10);
	private SMA sma180 = new SMA(180);

	public MA180ShortTermChart(IndicatorPeriod period) {
		//super(period);
	}

	public void onBar(Candle bar) {

//		sma5.onBar(bar);
//		sma10.onBar(bar);
//		sma180.onBar(bar);
//
//		MAPoint sma5Point = sma5.getLastPoint();
//		MAPoint sma10Point = sma10.getLastPoint();
//		MAPoint sma180Point = sma180.getLastPoint();
//
//		switch (openState) {
//		case OpenLong:
//			if (sma5Point.getYAxis() < sma180Point.getYAxis()) {
//				// 开空仓
//				addTradeSignal(TradeSignal.OpenShortSignal);
//				openState = Open.OpenShort;
//			}
//			break;
//		case OpenShort:
//			if (sma5Point.getYAxis() > sma180Point.getYAxis()) {
//				// 开多仓
//				addTradeSignal(TradeSignal.OpenLongSignal);
//				openState = Open.OpenLong;
//			}
//			break;
//		default:
//			break;
//		}
//		switch (closeState) {
//		case CloseLong:
//			if (sma5Point.getYAxis() > sma10Point.getYAxis()) {
//				// 平空仓
//				addTradeSignal(TradeSignal.CloseShortSignal);
//				closeState = Close.CloseShort;
//			}
//			break;
//		case CloseShort:
//			if (sma5Point.getYAxis() < sma10Point.getYAxis()) {
//				// 平多仓
//				addTradeSignal(TradeSignal.CloseLongSignal);
//				closeState = Close.CloseLong;
//			}
//			break;
//		default:
//			break;
//		}
	}


}
