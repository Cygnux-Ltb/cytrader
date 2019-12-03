package io.redstone.core.order.impl;

import io.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.StopLoss;

public abstract class ActualOrder extends AbstractOrder {

	protected ActualOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId, stopLoss);
	}

	protected ActualOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
	}

}
