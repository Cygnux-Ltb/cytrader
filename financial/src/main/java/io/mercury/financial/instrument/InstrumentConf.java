package io.mercury.financial.instrument;

import java.util.List;

public final class InstrumentConf {

	private String type;
	private List<String> instruments;

	public String getType() {
		return type;
	}

	public InstrumentConf setType(String type) {
		this.type = type;
		return this;
	}

	public List<String> getInstruments() {
		return instruments;
	}

	public InstrumentConf setInstruments(List<String> instruments) {
		this.instruments = instruments;
		return this;
	}

}
