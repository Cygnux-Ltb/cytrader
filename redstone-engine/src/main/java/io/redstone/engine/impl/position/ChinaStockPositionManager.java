package io.redstone.engine.impl.position;

import io.redstone.core.position.api.PositionProducer;
import io.redstone.core.position.impl.AbsPositionManager;

public class ChinaStockPositionManager extends AbsPositionManager<ChinaStockPosition> {

	private static final ChinaStockPositionProducer ProducerSingleton = new ChinaStockPositionProducer();

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ProducerSingleton);
	}

	private static class ChinaStockPositionProducer implements PositionProducer<ChinaStockPosition> {
		@Override
		public ChinaStockPosition produce(int accountId, int instrumentId, long qty) {
			return new ChinaStockPosition(accountId, instrumentId, qty);
		}
	}

}
