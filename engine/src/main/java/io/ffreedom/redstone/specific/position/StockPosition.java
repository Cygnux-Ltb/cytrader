package io.ffreedom.redstone.specific;

import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.base.OrdQtyPrice;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.position.AbsPosition;

public class StockPosition extends AbsPosition {

	private double availableQty;

	public StockPosition(int accountId, int instrumentId) {
		super(accountId, instrumentId);
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
				setCurrentQty(
						DoubleUtil.add8(getCurrentQty(), ordQtyPrice.getFilledQty() - ordQtyPrice.getLastFilledQty()));
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				setAvailableQty(DoubleUtil.subtraction(getAvailableQty(), ordQtyPrice.getOfferQty()));
				break;
			case Canceled:
			case NewRejected:
				setAvailableQty(
						DoubleUtil.add8(getAvailableQty(), ordQtyPrice.getOfferQty() - ordQtyPrice.getLastFilledQty()));
				break;
			case PartiallyFilled:
			case Filled:
				setCurrentQty(DoubleUtil.subtraction(getCurrentQty(),
						ordQtyPrice.getFilledQty() + ordQtyPrice.getLastFilledQty()));
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

	public double getAvailableQty() {
		return availableQty;
	}

	public StockPosition setAvailableQty(double availableQty) {
		this.availableQty = availableQty;
		return this;
	}

}
