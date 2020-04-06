package io.redstone.core.order.specific;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.OrderImpl;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdAction;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;

/**
 * 
 * 策略在出现交易信号后发出的订单, 可以对应一个或多个实际订单
 * 
 * @author yellow013
 */
public final class StrategyOrder extends OrderImpl {

	private MutableLongObjectMap<ParentOrder> actualOrders = MutableMaps.newLongObjectHashMap();

	public StrategyOrder(int strategyId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice,
			TrdDirection trdDirection, OrdType ordType, int subAccountId) {
		super(strategyId, instrument, ordQty, ordPrice, trdDirection, ordType, subAccountId);
	}

	public MutableLongObjectMap<ParentOrder> actualOrders() {
		return actualOrders;
	}

	public ParentOrder addActualOrder(ParentOrder parentOrder) {
		actualOrders.put(parentOrder.ordSysId(), parentOrder);
		return parentOrder;
	}

	@Override
	public OrdLevel ordLevel() {
		return OrdLevel.Strategy;
	}

	@Override
	public long strategyOrdId() {
		return ordSysId();
	}

	public ParentOrder toOpenActualOrder(TrdDirection trdDirection, long offerQty, OrdType ordType) {
		return addActualOrder(new ParentOrder(instrument(), offerQty, ordPrice().offerPrice(), trdDirection, ordType,
				TrdAction.Open, strategyId(), strategyOrdId(), subAccountId()));
	}

}
