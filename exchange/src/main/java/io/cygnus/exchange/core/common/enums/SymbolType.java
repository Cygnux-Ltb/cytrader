package io.cygnus.exchange.core.common.enums;

import lombok.Getter;

public enum SymbolType {

	CURRENCY_EXCHANGE_PAIR(0),

	FUTURES_CONTRACT(1),

	OPTION(2),

	;

	@Getter
	private final byte code;

	private SymbolType(int code) {
		this.code = (byte) code;
	}

	public static SymbolType of(int code) {
		switch (code) {
		case 0:
			return CURRENCY_EXCHANGE_PAIR;
		case 1:
			return FUTURES_CONTRACT;
		case 2:
			return OPTION;
		default:
			throw new IllegalStateException("unknown SymbolType code : " + code);
		}
//		return Arrays.stream(values()).filter(c -> c.code == (byte) code).findFirst()
//				.orElseThrow(() -> new IllegalStateException("unknown SymbolType code: " + code));
	}

}
