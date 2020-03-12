package io.mercury.ctp.adaptor.dto;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.adaptor.dto.SubscribeMarketData;

public final class CtpSubscribeMarketData extends SubscribeMarketData {

	public CtpSubscribeMarketData(Instrument... instruments) {
		super(instruments);
	}

}
