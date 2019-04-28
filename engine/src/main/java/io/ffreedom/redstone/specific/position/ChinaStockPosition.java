package io.ffreedom.redstone.specific.position;

import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.position.impl.GenericT1Position;

public class ChinaStockPosition extends GenericT1Position {

	public ChinaStockPosition(int accountId, int instrumentId, long tradeableQty) {
		super(accountId, instrumentId, tradeableQty);
	}

	@Override
	public void updatePosition(Order order) {
		OrdStatus status = order.getStatus();
		OrdQtyPrice ordQtyPrice = order.getQtyPrice();
		switch (order.getSide().direction()) {
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
