package io.redstone.adaptor.jctp.dto;

import io.polaris.financial.Instrument;
import io.redstone.core.adaptor.dto.SubscribeMarketData;

public final class CtpSubscribeMarketData extends SubscribeMarketData {

	public CtpSubscribeMarketData(Instrument... instruments) {
		super(instruments);
	}

}
