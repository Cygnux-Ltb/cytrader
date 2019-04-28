package io.ffreedom.redstone.core.order.impl;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.StopLoss;

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
