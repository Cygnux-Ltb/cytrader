package io.cygnus.engine.position;

import io.horizon.structure.market.instrument.impl.ChinaFutures;
import io.horizon.structure.position.AbstractPositionManager;

public final class ChinaFuturesPositionManager extends AbstractPositionManager<ChinaFuturesPosition> {

	public static final ChinaFuturesPositionManager Singleton = new ChinaFuturesPositionManager();

	private ChinaFuturesPositionManager() {
		super((accountId, instrument) -> {
			if (instrument instanceof ChinaFutures) {
				return new ChinaFuturesPosition(accountId, (ChinaFutures) instrument);
			} else {
				throw new IllegalArgumentException("Produce ChinaFutures Error");
			}
		});
	}

}
