package io.mercury.redstone.core.order.enums;

public enum OrdType {

	Limit(1),

	Market(2),

	Stop(3),

	StopLimit(4),

	FOK(5),

	FAK(6),

	Invalid(7),

	;

	private int code;

	private OrdType(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
