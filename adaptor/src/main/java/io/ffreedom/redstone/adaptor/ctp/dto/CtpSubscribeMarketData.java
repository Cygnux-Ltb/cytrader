package io.ffreedom.redstone.adaptor.ctp.dto;

import java.util.Collection;

import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;

public class CtpSubscribeMarketData extends SubscribeMarketData {

	public CtpSubscribeMarketData(Collection<String> insinstrumentIds) {
		super(insinstrumentIds);
	}

}
