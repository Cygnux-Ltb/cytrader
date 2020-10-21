package io.apollo.engine.position;

import io.apollo.core.position.impl.BasePositionManager;

public final class ChinaFuturesPositionManager extends BasePositionManager<ChinaFuturesPosition> {

	public final static ChinaFuturesPositionManager Singleton = new ChinaFuturesPositionManager();

	private ChinaFuturesPositionManager() {
		super(ChinaFuturesPosition::new);
	}

}
