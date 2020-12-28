package io.cygnus.engine.position;

import io.horizon.structure.position.PositionManagerBaseImpl;

public final class ChinaFuturesPositionManager extends PositionManagerBaseImpl<ChinaFuturesPosition> {

	public static final ChinaFuturesPositionManager Singleton = new ChinaFuturesPositionManager();

	private ChinaFuturesPositionManager() {
		super(ChinaFuturesPosition::new);
	}

}
