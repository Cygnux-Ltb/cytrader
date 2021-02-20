package io.cygnus.exchange.core.common.enums;

import lombok.Getter;

public enum OrderType {

	// Good till Cancel - equivalent to regular limit order
	GTC(0),

	// Immediate or Cancel - equivalent to strict-risk market order
	IOC(1), // with price cap
	IOC_BUDGET(2), // with total amount cap

	// Fill or Kill - execute immediately completely or not at all
	FOK(3), // with price cap
	FOK_BUDGET(4),// total amount cap

	;

	@Getter
	private final byte code;

	private OrderType(final int code) {
		this.code = (byte) code;
	}

	public static OrderType of(final byte code) {
		switch (code) {
		case 0:
			return GTC;
		case 1:
			return IOC;
		case 2:
			return IOC_BUDGET;
		case 3:
			return FOK;
		case 4:
			return FOK_BUDGET;
		default:
			throw new IllegalArgumentException("unknown OrderType code : " + code);
		}
	}

}
