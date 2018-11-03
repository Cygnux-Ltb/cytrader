package io.ffreedom.redstone.core.adaptor.req;

import java.util.Collection;

public class ReqSubscribeMarketData {

	private Collection<String> instrumentIdList;

	public ReqSubscribeMarketData(Collection<String> instrumentIdList) {
		super();
		this.instrumentIdList = instrumentIdList;
	}

	public Collection<String> getInstrumentIdList() {
		return instrumentIdList;
	}

}
