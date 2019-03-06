package io.ffreedom.redstone.adaptor.jctp.dto;

import java.util.Set;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;

public class CtpSubscribeMarketData extends SubscribeMarketData {

	public CtpSubscribeMarketData(Set<Instrument> instrumentSet) {
		super(instrumentSet);
	}

}
