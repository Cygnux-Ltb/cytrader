package io.cygnus.engine.position;

import io.horizon.structure.position.PositionManagerBaseImpl;

public final class ChinaStockPositionManager extends PositionManagerBaseImpl<ChinaStockPosition> {

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ChinaStockPosition::new);
	}

}
