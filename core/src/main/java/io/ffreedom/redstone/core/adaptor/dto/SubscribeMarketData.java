package io.ffreedom.redstone.core.adaptor.dto;

import java.util.Set;

import io.ffreedom.polaris.financial.Instrument;

public class SubscribeMarketData {

	private Set<Instrument> instrumentSet;

	public SubscribeMarketData(Set<Instrument> instrumentSet) {
		super();
		this.instrumentSet = instrumentSet;
	}

	public Set<Instrument> getInstrumentSet() {
		return instrumentSet;
	}

}
