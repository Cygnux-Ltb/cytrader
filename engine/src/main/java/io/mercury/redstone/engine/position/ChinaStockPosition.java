package io.mercury.redstone.engine.position;

import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.position.impl.AbsT1Position;

public class ChinaStockPosition extends AbsT1Position {

	public ChinaStockPosition(int accountId, int instrumentId, int tradeableQty) {
		super(accountId, instrumentId, tradeableQty);
	}

	@Override
	public void updatePosition(Order order) {
		OrdStatus status = order.status();
		OrdQty qty = order.qty();
		switch (order.direction()) {
		case Long:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				setCurrentQty(currentQty() + qty.filledQty() - qty.lastFilledQty());
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				setTradeableQty(tradeableQty() - qty.offerQty());
				break;
			case Canceled:
			case NewRejected:
				setTradeableQty(tradeableQty() + qty.offerQty() - qty.lastFilledQty());
				break;
			case PartiallyFilled:
			case Filled:
				setCurrentQty(currentQty() - qty.filledQty() + qty.lastFilledQty());
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

}
