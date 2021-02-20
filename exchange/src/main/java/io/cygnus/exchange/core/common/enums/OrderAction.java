package io.cygnus.exchange.core.common.enums;

import lombok.Getter;

public enum OrderAction {

	ASK(0),

	BID(1),

	;

	@Getter
	private final byte code;

	private OrderAction(int code) {
		this.code = (byte) code;
	}

	public static OrderAction of(byte code) {
		switch (code) {
		case 0:
			return ASK;
		case 1:
			return BID;
		default:
			throw new IllegalArgumentException("unknown OrderAction code : " + code);
		}
	}

	public OrderAction opposite() {
		return this == ASK ? BID : ASK;
	}

}
