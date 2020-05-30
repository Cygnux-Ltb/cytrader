package io.mercury.redstone.engine.position;

import io.mercury.redstone.core.position.impl.AbsPositionManager;

public final class ChinaFuturesPositionManager extends AbsPositionManager<ChinaFuturesPosition> {

	public final static ChinaFuturesPositionManager Singleton = new ChinaFuturesPositionManager();

	private ChinaFuturesPositionManager() {
		super(ChinaFuturesPosition::new);
	}

}
