package io.ffreedom.redstone.adaptor.ctp.dto;

import java.util.Collection;

import io.ffreedom.redstone.core.adaptor.req.ReqSubscribeMarketData;

public class CtpSubscribeMarketData extends ReqSubscribeMarketData {

	public CtpSubscribeMarketData(Collection<String> insinstrumentIds) {
		super(insinstrumentIds);
	}

}
