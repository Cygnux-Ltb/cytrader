package io.cygnus.exchange.core.common.enums;

import lombok.Getter;

public enum UserStatus {

	ACTIVE(0), // normal user

	SUSPENDED(1), // suspended

	;

	@Getter
	private byte code;

	private UserStatus(int code) {
		this.code = (byte) code;
	}

	public static UserStatus of(byte code) {
		switch (code) {
		case 0:
			return ACTIVE;
		case 1:
			return SUSPENDED;
		default:
			throw new IllegalArgumentException("unknown UserStatus code : " + code);
		}
	}

}
