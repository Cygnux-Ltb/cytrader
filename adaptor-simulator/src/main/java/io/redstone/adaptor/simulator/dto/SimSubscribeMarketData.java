package io.redstone.adaptor.simulator.dto;

import io.mercury.polaris.financial.instrument.Instrument;

public final class SimSubscribeMarketData {

	private Instrument[] instruments;
	private String investorId;
	private int clientId;
	private String startTradingDay;
	private String endTradingDay;

	public SimSubscribeMarketData(String investorId, int clientId, String startTradingDay, String endTradingDay,
			Instrument... instruments) {
		this.instruments = instruments;
		this.investorId = investorId;
		this.clientId = clientId;
		this.startTradingDay = startTradingDay;
		this.endTradingDay = endTradingDay;
	}

	public Instrument[] getInstruments() {
		return instruments;
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
