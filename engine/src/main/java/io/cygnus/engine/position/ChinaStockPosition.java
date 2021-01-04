package io.cygnus.engine.position;

import io.horizon.structure.market.instrument.impl.ChinaStock;
import io.horizon.structure.order.OrdQty;
import io.horizon.structure.order.Order;
import io.horizon.structure.order.enums.OrdStatus;
import io.horizon.structure.position.Position.PositionBaseImpl;
import io.mercury.serialization.json.JsonWrapper;

public class ChinaStockPosition extends PositionBaseImpl<ChinaStockPosition> {

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
	public ChinaStockPosition setTradeableQty(int tradeableQty) {
		this.tradeableQty = tradeableQty;
		return this;
	}

	@Override
	protected ChinaStockPosition returnSelf() {
		return this;
	}

	@Override
	public String toString() {
		return JsonWrapper.toJson(this);
	}

	@Override
	public void updateWithOrder(Order order) {
		OrdStatus status = order.getStatus();
		OrdQty qty = order.getQty();
		switch (order.getDirection()) {
		case Long:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				setCurrentQty(getCurrentQty() + qty.filledQty() - qty.lastFilledQty());
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				setTradeableQty(getTradeableQty() - qty.offerQty());
				break;
			case Canceled:
			case NewRejected:
				setTradeableQty(getTradeableQty() + qty.offerQty() - qty.lastFilledQty());
				break;
			case PartiallyFilled:
			case Filled:
				setCurrentQty(getCurrentQty() - qty.filledQty() + qty.lastFilledQty());
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

}
