package io.redstone.engine.impl.position;

import io.redstone.core.position.impl.AbsPositionManager;

public class ChinaStockPositionManager extends AbsPositionManager<ChinaStockPosition> {

	public static final ChinaStockPositionManager Singleton = new ChinaStockPositionManager();

	private ChinaStockPositionManager() {
		super(ChinaStockPosition::new);
	}

}
