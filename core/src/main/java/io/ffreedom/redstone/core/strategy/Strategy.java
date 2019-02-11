package io.ffreedom.redstone.core.strategy;

import io.ffreedom.common.functional.Initializer;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;

public interface Strategy {

	int getStrategyId();

	boolean isEnable();

	void init(Initializer<Boolean> initializer);

	void onControlEvent(StrategyControlEvent event);

	void onMarketData(BasicMarketData marketData);

	void onOrder(Order order);

	void onError(Throwable throwable);

	void positionTarget(Instrument instrument, double targetQty, double minPrice, double maxPrice);

}
