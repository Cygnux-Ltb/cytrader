package io.cygnus.exchange.core.common.enums;

import lombok.Getter;

public enum BalanceAdjustmentType {

	ADJUSTMENT(0),

	SUSPEND(1),

	;

	@Getter
	private final byte code;

	private BalanceAdjustmentType(int code) {
		this.code = (byte) code;
	}

	public static BalanceAdjustmentType of(byte code) {
		switch (code) {
		case 0:
			return ADJUSTMENT;
		case 1:
			return SUSPEND;
		default:
			throw new IllegalArgumentException("unknown BalanceAdjustmentType code : " + code);
		}
	}

}
