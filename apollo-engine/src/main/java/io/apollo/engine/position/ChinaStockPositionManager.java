package io.apollo.engine.position;

import io.apollo.core.position.impl.AbsPositionManager;

public class ChinaStockPositionManager extends AbsPositionManager<ChinaStockPosition> {

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ChinaStockPosition::new);
	}

}
