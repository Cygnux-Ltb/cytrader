package io.redstone.adaptor.simulator.dto;

import io.polaris.financial.instrument.Instrument;
import io.redstone.core.adaptor.dto.SubscribeMarketData;

public final class SimSubscribeMarketData extends SubscribeMarketData {

	private String investorId;
	private int clientId;
	private String startTradingDay;
	private String endTradingDay;

	public SimSubscribeMarketData(String investorId, int clientId, String startTradingDay, String endTradingDay,
			Instrument... instrumentSet) {
		super(instrumentSet);
		this.investorId = investorId;
		this.clientId = clientId;
		this.startTradingDay = startTradingDay;
		this.endTradingDay = endTradingDay;
	}

	public String getInvestorId() {
		return investorId;
	}

	public int getClientId() {
		return clientId;
	}

	public String getStartTradingDay() {
		return startTradingDay;
	}

	public String getEndTradingDay() {
		return endTradingDay;
	}

}
