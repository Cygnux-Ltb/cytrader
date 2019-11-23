package io.redstone.core.position.impl;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collections.MutableMaps;
import io.redstone.core.position.api.Position;
import io.redstone.core.position.api.PositionProducer;

/**
 * 实际账户的持仓集合
 * 
 * @author yellow013
 * @creation 2018年5月14日
 * @param <T>
 */
public final class PositionSet<T extends Position> {

	private int accountId;

	// Map<instrumentId, Position>
	private MutableIntObjectMap<T> positionMap = MutableMaps.newIntObjectHashMap();

	private PositionProducer<T> positionProducer;

	public PositionSet(int accountId, PositionProducer<T> positionProducer) {
		this.accountId = accountId;
		this.positionProducer = positionProducer;
	}

	public int getAccountId() {
		return accountId;
	}

	public void putPosition(T position) {
		positionMap.put(position.getInstrumentId(), position);
	}

	public T getPosition(int instrumentId) {
		T position = positionMap.get(instrumentId);
		if (position == null) {
			position = positionProducer.produce(accountId, instrumentId);
			positionMap.put(instrumentId, position);
		}
		return position;
	}

}
