package io.ffreedom.redstone.specific.position;

import io.ffreedom.redstone.core.position.api.PositionProducer;
import io.ffreedom.redstone.core.position.impl.AbsPositionManager;

public final class ChinaFuturesPositionManager extends AbsPositionManager<ChinaFuturesPosition> {

	private final static ChinaFuturesPositionProducer ProducerSingleton = new ChinaFuturesPositionProducer();

	public final static ChinaFuturesPositionManager Singleton = new ChinaFuturesPositionManager();

	private ChinaFuturesPositionManager() {
		super(ProducerSingleton);
	}

	private static class ChinaFuturesPositionProducer implements PositionProducer<ChinaFuturesPosition> {
		@Override
		public ChinaFuturesPosition produce(int accountId, int instrumentId, long qty) {
			return ChinaFuturesPosition.newInstance(accountId, instrumentId, qty);
		}
	}

}
