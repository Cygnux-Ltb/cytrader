package io.ffreedom.redstone.core.position;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.position.api.Position;
import io.ffreedom.redstone.core.position.api.PositionProducer;

public final class Positions<T extends Position> {

	private int accountId;

	// Map<instrumentId, Position>
	private MutableIntObjectMap<T> positionMap = MutableMaps.newIntObjectHashMap();

	private PositionProducer<T> positionFactory;

	public Positions(int accountId, PositionProducer<T> positionFactory) {
		this.accountId = accountId;
		this.positionFactory = positionFactory;
	}

	public int getAccountId() {
		return accountId;
	}

	public void onOrder(Order order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		T position = positionMap.get(instrumentId);
		if (position == null) {
			position = positionFactory.produce(accountId, instrumentId);
			positionMap.put(instrumentId, position);
		}
		position.updatePosition(order);
	}

}
