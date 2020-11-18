package io.apollo.engine.position;

import io.horizon.definition.position.PositionManagerBaseImpl;

public final class ChinaStockPositionManager extends PositionManagerBaseImpl<ChinaStockPosition> {

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ChinaStockPosition::new);
	}

}
