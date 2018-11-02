package io.ffreedom.redstone.core.assets;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.redstone.core.order.Order;

public class Positions {

	private int accountId;
	// Map<instrumentId, Position>
	private MutableIntObjectMap<Position> positionMap = IntObjectHashMap.newMap();

	public Positions(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void onOrder(Order order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		Position position = positionMap.get(instrumentId);
		if (position == null) {
			position = Position.create(instrumentId);
			positionMap.put(instrumentId, position);
		}
		switch (order.getOrdSide()) {
		case BUY:
		case MARGIN_BUY:
			position.setCurrentQty(DoubleUtil.add8(position.getCurrentQty(), order.getOrdQtyPrice().getFilledQty()));
			break;
		case SELL:
		case SHORT_SELL:
			position.setCurrentQty(
					DoubleUtil.subtraction(position.getCurrentQty(), order.getOrdQtyPrice().getFilledQty()));
		default:
			break;
		}
	}

}
