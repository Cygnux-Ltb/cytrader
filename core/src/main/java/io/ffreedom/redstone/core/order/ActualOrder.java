package io.ffreedom.redstone.core.order;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;
import io.ffreedom.redstone.core.order.structure.StopLoss;
import io.ffreedom.redstone.core.order.utils.OrdSysIdGenerate;

public abstract class ActualOrder extends AbstractOrder {

	protected ActualOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			OrdStatus ordStatus, OrdTimestamps ordTimestamps, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(OrdSysIdGenerate.next(strategyId), instrument, ordQtyPrice, ordSide, ordType, ordStatus, ordTimestamps,
				strategyId, subAccountId, stopLoss);
	}

}
