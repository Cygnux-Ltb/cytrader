package io.apollo.core.position.impl;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.apollo.core.position.api.Position;
import io.apollo.core.position.api.PositionManager;
import io.apollo.core.position.api.PositionProducer;
import io.mercury.common.collections.MutableMaps;

public abstract class AbsPositionManager<T extends Position> implements PositionManager<T> {

	private MutableIntObjectMap<PositionSet<T>> positionSetMap = MutableMaps.newIntObjectHashMap();

	private PositionProducer<T> producer;

	protected AbsPositionManager(PositionProducer<T> producer) {
		this.producer = producer;
	}

	@Override
	public void putPosition(T position) {
		getPositionSet(position.accountId()).putPosition(position);
	}

	@Override
	public T getPosition(int accountId, int instrumentId) {
		return getPositionSet(accountId).getPosition(instrumentId);
	}

	private PositionSet<T> getPositionSet(int accountId) {
		PositionSet<T> positionSet = positionSetMap.get(accountId);
		if (positionSet == null) {
			positionSet = new PositionSet<>(accountId, producer);
			positionSetMap.put(accountId, positionSet);
		}
		return positionSet;
	}

}
