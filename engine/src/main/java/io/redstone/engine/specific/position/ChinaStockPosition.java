package io.redstone.engine.specific.position;

import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.structure.OrdQtyPrice;
import io.redstone.core.position.impl.AbsT1Position;

public class ChinaStockPosition extends AbsT1Position {

	public ChinaStockPosition(int accountId, int instrumentId, long tradeableQty) {
		super(accountId, instrumentId, tradeableQty);
	}

	@Override
	public void updatePosition(Order order) {
		OrdStatus status = order.status();
		OrdQtyPrice ordQtyPrice = order.qtyPrice();
		switch (order.side().direction()) {
		case Long:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				setCurrentQty(getCurrentQty() + ordQtyPrice.getFilledQty() - ordQtyPrice.getLastFilledQty());
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				setTradeableQty(getTradeableQty() - ordQtyPrice.getOfferQty());
				break;
			case Canceled:
			case NewRejected:
				setTradeableQty(getTradeableQty() + ordQtyPrice.getOfferQty() - ordQtyPrice.getLastFilledQty());
				break;
			case PartiallyFilled:
			case Filled:
				setCurrentQty(getCurrentQty() - ordQtyPrice.getFilledQty() + ordQtyPrice.getLastFilledQty());
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

}
