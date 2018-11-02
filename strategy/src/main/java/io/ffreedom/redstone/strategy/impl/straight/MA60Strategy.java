package io.ffreedom.redstone.strategy.impl.straight;

import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;
import io.ffreedom.redstone.strategy.impl.straight.chart.MA60Chart;

public class MA60Strategy extends BaseStrategy {

	private MA60Chart h1;
	private MA60Chart h4;

	public MA60Strategy() {
		super(3);
		this.h1 = new MA60Chart(IndicatorPeriod.H1);
	}

	@Override
	public void init() {

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
