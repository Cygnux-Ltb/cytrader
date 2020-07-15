package io.mercury.financial.instrument;

import java.time.ZoneId;

import io.mercury.common.datetime.TimeZone;

public enum Exchange {

	TOCOM(11, "Tokyo Commodity Exchange", TimeZone.JST),

	TFX(12, "Tokyo Financial Exchange", TimeZone.JST),

	LME(22, "London Metal Exchange", TimeZone.UTC),

	SHFE(41, "Shanghai Futures Exchange", TimeZone.CST),

	DCE(42, "Dalian Commodity Exchange", TimeZone.CST),

	ZCE(43, "Zhengzhou Commodity Exchange", TimeZone.CST),

	CFFEX(44, "China Financial Futures Exchange", TimeZone.CST),

	SHINE(45, "Shanghai International Energy Exchange", TimeZone.CST),

	SSE(46, "Shanghai Stock Exchange", TimeZone.CST),

	SZSE(47, "Shenzhen Stock Exchange", TimeZone.CST),

	;

	private int id;
	private String desc;
	private ZoneId zoneId;

	private Exchange(int id, String desc, ZoneId zoneId) {
		this.id = id * 1000000;
		this.zoneId = zoneId;
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}

	public String code() {
		return name();
	}

	public ZoneId zoneId() {
		return zoneId;
	}

}
