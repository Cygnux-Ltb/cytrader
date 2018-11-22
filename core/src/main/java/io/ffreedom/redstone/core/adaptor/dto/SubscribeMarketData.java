package io.ffreedom.redstone.core.adaptor.dto;

import java.util.Collection;

public class SubscribeMarketData {

	private Collection<String> instrumentIdList;

	public SubscribeMarketData(Collection<String> instrumentIdList) {
		super();
		this.instrumentIdList = instrumentIdList;
	}

	public Collection<String> getInstrumentIdList() {
		return instrumentIdList;
	}

}
