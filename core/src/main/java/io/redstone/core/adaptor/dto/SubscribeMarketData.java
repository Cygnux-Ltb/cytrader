package io.redstone.core.adaptor.dto;

import org.eclipse.collections.api.set.ImmutableSet;

import io.ffreedom.common.collections.ImmutableSets;
import io.polaris.financial.instrument.Instrument;

public class SubscribeMarketData {

	private ImmutableSet<Instrument> instrumentSet;

	protected SubscribeMarketData(Instrument... instruments) {
		this.instrumentSet = ImmutableSets.newSet(instruments);
	}

	public static SubscribeMarketData build(Instrument... instruments) {
		return new SubscribeMarketData(instruments);
	}

	public ImmutableSet<Instrument> getInstrumentSet() {
		return instrumentSet;
	}

}
