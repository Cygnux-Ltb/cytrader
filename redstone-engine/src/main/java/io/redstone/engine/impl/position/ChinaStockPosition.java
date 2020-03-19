package io.redstone.engine.impl.position;

import io.redstone.core.order.Order;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.position.impl.AbsT1Position;

public class ChinaStockPosition extends AbsT1Position {

	public ChinaStockPosition(int accountId, int instrumentId, long tradeableQty) {
		super(accountId, instrumentId, tradeableQty);
	}

	@Override
	public void updatePosition(Order order) {
		OrdStatus status = order.ordStatus();
		OrdQty qty = order.ordQty();
		switch (order.ordSide().direction()) {
		case Long:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				currentQty(currentQty() + qty.filledQty() - qty.lastFilledQty());
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				tradeableQty(tradeableQty() - qty.offerQty());
				break;
			case Canceled:
			case NewRejected:
				tradeableQty(tradeableQty() + qty.offerQty() - qty.lastFilledQty());
				break;
			case PartiallyFilled:
			case Filled:
				currentQty(currentQty() - qty.filledQty() + qty.lastFilledQty());
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

}
