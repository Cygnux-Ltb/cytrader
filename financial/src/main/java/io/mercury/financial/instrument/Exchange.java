package io.mercury.financial.instrument;

import java.time.ZoneId;

import io.mercury.common.datetime.TimeZones;

public enum Exchange {

	// Shanghai Futures Exchange
	SHFE(41, TimeZones.CST),

	// Zhengzhou Commodity Exchange
	ZCE(42, TimeZones.CST),

	// Dalian Commodity Exchange
	DCE(43, TimeZones.CST),

	// China Financial Futures Exchange
	CFFE(44, TimeZones.CST),

	// Shanghai International Energy Exchange
	SIEE(45, TimeZones.CST),

	// Tokyo Commodity Exchange
	TOCOM(11, TimeZones.JST),

	;

	private int exchangeId;

	private ZoneId zoneId;

	private Exchange(int exchangeId, ZoneId zoneId) {
		this.exchangeId = exchangeId * 10000000;
		this.zoneId = zoneId;

	}

	public int id() {
		return exchangeId;
	}

	public String code() {
		return name();
	}

	public ZoneId zoneId() {
		return zoneId;
	}

}
