package io.ffreedom.redstone.strategy.impl.base;

import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.actor.InstrumentActor;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;
import io.ffreedom.redstone.core.strategy.Strategy;

public abstract class BaseStrategy implements Strategy {

	protected OutboundAdaptor adaptor;

	private int strategyId;

	private boolean isTradable;

	public BaseStrategy(int strategyId) {
		super();
		this.strategyId = strategyId;
	}

	@Override
	public int getStrategyId() {
		return strategyId;
	}

	@Override
	public boolean isTradable() {
		return isTradable;
	}

	@Override
	public void openStrategy() {
		isTradable = true;
	}

	@Override
	public void closeStrategy() {
		isTradable = false;
	}

	@Override
	public void openInstrument(Instrument instrument) {
		InstrumentActor.setTradeable(instrument);
	}

	@Override
	public void closeInstrument(Instrument instrument) {
		InstrumentActor.setNotTradeable(instrument);
	}

	@Override
	public void positionTarget(Instrument instrument, double targetQty, double minPrice, double maxPrice) {
		// TODO Auto-generated method stub

	}

}
