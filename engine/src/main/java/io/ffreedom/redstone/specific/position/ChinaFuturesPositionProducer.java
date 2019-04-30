package io.ffreedom.redstone.specific.position;

import io.ffreedom.redstone.core.position.api.PositionProducer;

public class ChinaFuturesPositionProducer implements PositionProducer<ChinaFuturesPosition> {

	@Override
	public ChinaFuturesPosition produce(int accountId, int instrumentId) {
		return ChinaFuturesPosition.newInstance(accountId, instrumentId);
	}

	@Override
	public ChinaFuturesPosition produce(int accountId, int instrumentId, long qty) {
		return ChinaFuturesPosition.newInstance(accountId, instrumentId, qty);
	}

}
