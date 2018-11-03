package io.ffreedom.redstone.strategy.impl.straight;

import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;
import io.ffreedom.redstone.strategy.impl.straight.chart.MA180MidTermChart;

public class MA180MidTermStrategy extends BaseStrategy {

	public MA180MidTermStrategy(int strategyId) {
		super(strategyId);
		// TODO Auto-generated constructor stub
	}

	private MA180MidTermChart m15;
	private MA180MidTermChart m30;
	private MA180MidTermChart h1;

	public MA180MidTermStrategy() {
		super(2);
		this.m15 = new MA180MidTermChart(IndicatorPeriod.M15);
		this.m30 = new MA180MidTermChart(IndicatorPeriod.M30);
		this.h1 = new MA180MidTermChart(IndicatorPeriod.H1);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

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

}
