package io.mercury.redstone.core.order.specific;

import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.OrderBaseImpl;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;

public abstract class ActualOrder extends OrderBaseImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6034876220144503779L;

	/**
	 * 
	 */
	private final TrdAction action;

	/**
	 * 所属上级OrderId
	 */
	private final long ownerOrdId;

	/**
	 * 
	 * @param strategyId
	 * @param subAccountId
	 * @param instrument
	 * @param ordQty
	 * @param ordPrice
	 * @param ordType
	 * @param direction
	 * @param action
	 * @param ownerOrdId
	 */
	protected ActualOrder(int strategyId, int subAccountId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice,
			OrdType ordType, TrdDirection direction, TrdAction action, long ownerOrdId) {
		super(strategyId, subAccountId, instrument, ordQty, ordPrice, ordType, direction);
		this.action = action;
		this.ownerOrdId = ownerOrdId != 0L ? ownerOrdId : ordSysId();
	}

	@Override
	public long ownerOrdId() {
		return ownerOrdId;
	}

	public TrdAction action() {
		return action;
	}

}
