package io.redstone.core.order.impl;

import io.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdQtyPrice;
import io.redstone.core.order.structure.StopLoss;

public abstract class ActualOrder extends AbstractOrder {

	protected ActualOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, ordQtyPrice, ordSide, ordType, strategyId, subAccountId, stopLoss);
	}

	protected ActualOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		super(instrument, ordQtyPrice, ordSide, ordType, strategyId, subAccountId);
	}

}
