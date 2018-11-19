package io.ffreedom.redstone.core.position;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.order.OrdQtyPrice;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.enums.OrdStatus;

public class Positions {

	private int accountId;
	// Map<instrumentId, Position>
	private MutableIntObjectMap<Position> positionMap = EclipseCollections.newIntObjectHashMap();

	public Positions(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void onOrder(Order order) {
		Instrument instrument = order.getInstrument();
		int instrumentId = instrument.getInstrumentId();
		Position position = positionMap.get(instrumentId);
		if (position == null) {
			position = Position.newInstance(instrument);
			positionMap.put(instrumentId, position);
		}
		OrdStatus status = order.getStatus();
		OrdQtyPrice ordQtyPrice = order.getQtyPrice();
		switch (order.getSide().direction()) {
		case Long:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				position.setCurrentQty(DoubleUtil.add8(position.getCurrentQty(),
						ordQtyPrice.getFilledQty() - ordQtyPrice.getLastFilledQty()));
				if (instrument.isTZero())
					position.setAvailableQty(DoubleUtil.add8(position.getAvailableQty(),
							ordQtyPrice.getFilledQty() - ordQtyPrice.getLastFilledQty()));
				break;
			default:
				break;
			}
			break;
		case Short:
			switch (status) {
			case PendingNew:
				position.getAvailableQty();
				
				position.setAvailableQty(DoubleUtil.subtraction(position.getAvailableQty(), ordQtyPrice.getOfferQty()));
			case Canceled:
			case NewRejected:
				position.setAvailableQty(DoubleUtil.add8(position.getAvailableQty(),
						ordQtyPrice.getOfferQty() - ordQtyPrice.getLastFilledQty()));
				break;
			case PartiallyFilled:
			case Filled:
				position.setCurrentQty(DoubleUtil.subtraction(position.getCurrentQty(),
						ordQtyPrice.getFilledQty() + ordQtyPrice.getLastFilledQty()));
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

}
