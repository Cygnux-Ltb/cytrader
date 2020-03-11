package io.redstone.core.order.impl;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;

/**
 * 
 * 策略在出现交易信号后发出的订单, 可以对应一个或多个实际订单
 * 
 * @author yellow013
 */
public final class StrategyOrder extends AbstractOrder {

	private MutableLongObjectMap<ParentOrder> actualOrders = MutableMaps.newLongObjectHashMap();

	private StrategyOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
	}

	public static StrategyOrder newStrategyOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice,
			OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId) {
		return new StrategyOrder(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
	}

	public MutableLongObjectMap<ParentOrder> getActualOrders() {
		return actualOrders;
	}

	public void addActualOrder(ParentOrder parentOrder) {
		actualOrders.put(parentOrder.ordSysId(), parentOrder);
	}

	@Override
	public OrdLevel ordLevel() {
		return OrdLevel.Strategy;
	}

	public long getVirtualId() {
		return ordSysId();
	}

}
