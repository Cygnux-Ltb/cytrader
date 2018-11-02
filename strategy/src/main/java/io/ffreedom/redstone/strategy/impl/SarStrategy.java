package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.indicators.impl.sar.SAR;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

/**
 * 
 * @author yellow013
 * @version 0.1
 * 
 *          Index SAR Strategy
 *
 */
public class SarStrategy extends BaseStrategy {

	private SAR sar = new SAR();

	public SarStrategy(int strategyId) {
		super(strategyId);
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
