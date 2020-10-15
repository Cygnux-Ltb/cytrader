package io.apollo.engine.position;

import io.apollo.core.position.impl.AbsPositionManager;

public final class ChinaFuturesPositionManager extends AbsPositionManager<ChinaFuturesPosition> {

	public final static ChinaFuturesPositionManager Singleton = new ChinaFuturesPositionManager();

	private ChinaFuturesPositionManager() {
		super(ChinaFuturesPosition::new);
	}

}
