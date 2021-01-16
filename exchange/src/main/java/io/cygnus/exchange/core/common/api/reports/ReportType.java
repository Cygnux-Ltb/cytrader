package io.cygnus.exchange.core.common.api.reports;

import lombok.Getter;


public enum ReportType {

	STATE_HASH(10001),

	SINGLE_USER_REPORT(10002),

	TOTAL_CURRENCY_BALANCE(10003),

	;

	@Getter
	private final int code;

	ReportType(int code) {
		this.code = code;
	}

	public static ReportType of(int code) {

		switch (code) {
		case 10001:
			return STATE_HASH;
		case 10002:
			return SINGLE_USER_REPORT;
		case 10003:
			return TOTAL_CURRENCY_BALANCE;
		default:
			throw new IllegalArgumentException("unknown ReportType:" + code);
		}

	}

}
