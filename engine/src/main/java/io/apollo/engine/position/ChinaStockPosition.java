package io.apollo.engine.position;

import io.gemini.definition.order.Order;
import io.gemini.definition.order.enums.OrdStatus;
import io.gemini.definition.order.structure.OrdQty;
import io.gemini.definition.position.PositionT1;

public class ChinaStockPosition extends PositionT1 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4167492706203838544L;

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
