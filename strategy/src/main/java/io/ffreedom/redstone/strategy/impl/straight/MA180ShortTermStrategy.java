package io.ffreedom.redstone.strategy.impl.straight;

import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.indicators.impl.candle.Candle;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.specific.IndicatorCallback;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;
import io.ffreedom.redstone.strategy.impl.straight.chart.MA180ShortTermChart;

public class MA180ShortTermStrategy extends BaseStrategy implements IndicatorCallback<Candle> {

	public MA180ShortTermStrategy(int strategyId) {
		super(strategyId);
		// TODO Auto-generated constructor stub
	}

	private MA180ShortTermChart s15;
	private MA180ShortTermChart m1;
	private MA180ShortTermChart m5;

	private MA180ShortTermChart m15;
	private MA180ShortTermChart m30;

	private MA180ShortTermChart h1;

	public MA180ShortTermStrategy() {
		super(1);
		this.s15 = new MA180ShortTermChart(IndicatorPeriod.S15);
		this.m1 = new MA180ShortTermChart(IndicatorPeriod.M1);
		this.m5 = new MA180ShortTermChart(IndicatorPeriod.M5);
		this.m15 = new MA180ShortTermChart(IndicatorPeriod.M15);
		this.m30 = new MA180ShortTermChart(IndicatorPeriod.M30);
		this.h1 = new MA180ShortTermChart(IndicatorPeriod.H1);

	}

	@Override
	public void onNewOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNewOrderReject(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCancelOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCancelOrderReject(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOrderFilled(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOrderPartiallyFilled(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketData(MarketData marketData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartPoint(Candle p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onEndPoint(Candle p) {
		// TODO Auto-generated method stub
	}

}
