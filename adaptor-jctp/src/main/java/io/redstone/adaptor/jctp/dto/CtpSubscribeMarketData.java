package io.ffreedom.redstone.adaptor.jctp.dto;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;

public final class CtpSubscribeMarketData extends SubscribeMarketData {

	public CtpSubscribeMarketData(Instrument... instruments) {
		super(instruments);
	}

}
