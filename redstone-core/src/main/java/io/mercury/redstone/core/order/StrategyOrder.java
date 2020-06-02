package io.mercury.redstone.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;

/**
 * 
 * 策略在出现交易信号后发出的订单, 可以对应一个或多个实际订单
 * 
 * @author yellow013
 */

@Deprecated
public final class StrategyOrder extends OrderBaseImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1630590012253172782L;

	/**
	 * 所属实际订单
	 */
	private MutableLongObjectMap<ActParentOrder> ownOrders = MutableMaps.newLongObjectHashMap();

	/**
	 * 
	 * @param strategyId
	 * @param subAccountId
	 * @param instrument
	 * @param ordQty
	 * @param ordPrice
	 * @param ordType
	 * @param direction
	 */
	public StrategyOrder(int strategyId, int accountId, int subAccountId, Instrument instrument, OrdQty ordQty,
			OrdPrice ordPrice, OrdType ordType, TrdDirection direction) {
		super(0L, strategyId, accountId, subAccountId, instrument, ordQty, ordPrice, ordType, direction);
	}

	public MutableLongObjectMap<ActParentOrder> ownOrders() {
		return ownOrders;
	}

	public ActParentOrder addOwnOrder(ActParentOrder order) {
		ownOrders.put(order.ordSysId(), order);
		return order;
	}

	@Override
	public int ordLevel() {
		return 2;
	}

	@Override
	public long ownerOrdId() {
		return ordSysId();
	}

	public ActParentOrder toActualOrder(TrdDirection direction, int offerQty, OrdType ordType) {
		return addOwnOrder(new ActParentOrder(strategyId(), accountId(), subAccountId(), instrument(), offerQty,
				ordPrice().offerPrice(), ordType, direction, TrdAction.Open, ordSysId()));
	}

}
