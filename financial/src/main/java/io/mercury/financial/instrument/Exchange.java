package io.mercury.financial.instrument;

import java.time.ZoneId;

import io.mercury.common.datetime.TimeZone;

public enum Exchange {

	// Shanghai Futures Exchange
	SHFE(41, TimeZone.CST),

	// Dalian Commodity Exchange
	DCE(42, TimeZone.CST),

	// Zhengzhou Commodity Exchange
	ZCE(43, TimeZone.CST),

	// China Financial Futures Exchange
	CFFE(44, TimeZone.CST),

	// Shanghai International Energy Exchange
	SIEE(45, TimeZone.CST),

	// Tokyo Commodity Exchange
	TOCOM(11, TimeZone.JST),

	;

	private int exchangeId;

	private ZoneId zoneId;

	private Exchange(int exchangeId, ZoneId zoneId) {
		this.exchangeId = exchangeId * 1000000;
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
