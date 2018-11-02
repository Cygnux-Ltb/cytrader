package io.ffreedom.redstone.core.trade;

import io.ffreedom.redstone.core.trade.enums.Action;
import io.ffreedom.redstone.core.trade.enums.Direction;

public interface TradeSignal {

	double getPrice();
	
	double getQty();
	
	Action getAction();

	Direction getDirection();

}
