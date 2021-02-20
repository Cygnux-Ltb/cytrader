package io.cygnus.runtime.config.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class LastPrice {

	@JSONField(name = "InstrumentID")
	private String instrumentId;

	@JSONField(name = "LastPrice")
	private double lastPrice;

	public String getInstrumentId() {
		return instrumentId;
	}

	public LastPrice setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
		return this;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public LastPrice setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

}
