package io.apollo.engine.position;

import io.gemini.definition.position.PositionManagerBaseImpl;

public final class ChinaStockPositionManager extends PositionManagerBaseImpl<ChinaStockPosition> {

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ChinaStockPosition::new);
	}

}
