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

	private int id;

	private ZoneId zoneId;

	private Exchange(int id, ZoneId zoneId) {
		this.id = id * 1000000;
		this.zoneId = zoneId;
	}

	public int id() {
		return id;
	}

	public String code() {
		return name();
	}

	public ZoneId zoneId() {
		return zoneId;
	}

}
