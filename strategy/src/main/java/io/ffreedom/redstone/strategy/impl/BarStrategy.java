package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.financial.Instrument;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.trade.enums.Direction;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

public class BarStrategy extends BaseStrategy {

	public BarStrategy(int strategyId) {
		super(strategyId);
		// TODO Auto-generated constructor stub
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

	@Override
	public void positionsTo(int strategyId, Instrument instrument, Direction direction, double price, double qty) {

	}

}
