package io.apollo.engine.position;

import io.apollo.core.position.impl.BasePositionManager;

public class ChinaStockPositionManager extends BasePositionManager<ChinaStockPosition> {

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ChinaStockPosition::new);
	}

}
