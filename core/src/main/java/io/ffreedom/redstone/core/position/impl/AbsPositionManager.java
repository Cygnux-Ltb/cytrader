package io.ffreedom.redstone.core.position.impl;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.redstone.core.position.api.Position;
import io.ffreedom.redstone.core.position.api.PositionManager;
import io.ffreedom.redstone.core.position.api.PositionProducer;

public abstract class AbsPositionManager<T extends Position> implements PositionManager<T> {

	private MutableIntObjectMap<PositionSet<T>> positionSetMap = MutableMaps.newIntObjectHashMap();

	private PositionProducer<T> producer;

	protected AbsPositionManager(PositionProducer<T> producer) {
		this.producer = producer;
	}

	@Override
	public void putPosition(T position) {
		getPositionSet(position.getAccountId()).putPosition(position);
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
