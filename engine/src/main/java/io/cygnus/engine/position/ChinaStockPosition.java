package io.cygnus.engine.position;

import io.horizon.structure.market.instrument.impl.ChinaStock;
import io.horizon.structure.order.OrdQty;
import io.horizon.structure.order.Order;
import io.horizon.structure.order.enums.OrdStatus;
import io.horizon.structure.position.AbstractPosition;
import io.mercury.serialization.json.JsonWrapper;

public class ChinaStockPosition extends AbstractPosition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4167492706203838544L;

	// 可交易的数量
	private int tradeableQty;

	public ChinaStockPosition(int accountId, ChinaStock stock) {
		super(accountId, stock);
	}

	@Override
	public int getTradeableQty() {
		return tradeableQty;
	}

	@Override
	public void setTradeableQty(int tradeableQty) {
		this.tradeableQty = tradeableQty;
	}

	@Override
	public String toString() {
		return JsonWrapper.toJson(this);
	}

	@Override
	public void updateWithOrder(Order order) {
		final OrdStatus status = order.getStatus();
		final OrdQty qty = order.getQty();
		switch (order.getDirection()) {
		case Long:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				setCurrentQty(getCurrentQty() + qty.getFilledQty() - qty.getLastFilledQty());
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				setTradeableQty(getTradeableQty() - qty.getOfferQty());
				break;
			case Canceled:
			case NewRejected:
				setTradeableQty(getTradeableQty() + qty.getOfferQty() - qty.getLastFilledQty());
				break;
			case PartiallyFilled:
			case Filled:
				setCurrentQty(getCurrentQty() - qty.getFilledQty() + qty.getLastFilledQty());
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

}
