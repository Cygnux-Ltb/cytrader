package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.indicators.impl.ma.SMA;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

public class SmaStrategy extends BaseStrategy {

	private SMA sma;

	public SmaStrategy(int period, int strategyId) {
		super(strategyId);
		sma = new SMA(period);
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
