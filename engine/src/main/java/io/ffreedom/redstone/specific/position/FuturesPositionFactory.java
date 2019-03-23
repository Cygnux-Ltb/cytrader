package io.ffreedom.redstone.specific;

import io.ffreedom.redstone.core.position.PositionFactory;

public class FuturesPositionFactory implements PositionFactory<FuturesPosition> {

	@Override
	public FuturesPosition produce(int accountId, int instrumentId) {
		return FuturesPosition.newInstance(accountId, instrumentId);
	}

	@Override
	public FuturesPosition produce(int accountId, int instrumentId, double qty) {
		return FuturesPosition.newInstance(accountId, instrumentId, qty);
	}

}
