package io.redstone.engine.impl.strategy;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.redstone.core.adaptor.Adaptor;

public abstract class SingleInstrumentStrategy<M extends MarketData> extends StrategyBaseImpl<M> {

	// 策略订阅的合约
	protected Instrument instrument;

	private ImmutableList<Instrument> instruments;

	protected SingleInstrumentStrategy(int strategyId, String strategyName, int subAccountId, Instrument instrument) {
		super(strategyId, strategyName, subAccountId);
		this.instrument = instrument;
		this.instruments = ImmutableLists.newList(instrument);
	}

	@Override
	public ImmutableList<Instrument> instruments() {
		return instruments;
	}
	
	private Adaptor adaptor;

	@Override
	public void addAdaptor(Adaptor adaptor) {
		this.adaptor = adaptor;
	}

	@Override
	protected Adaptor getAdaptor(Instrument instrument) {
		return adaptor;
	}


}
