package io.mercury.redstone.core.order.enums;

public enum OrdLevel {

	Group(0b0001),

	Strategy(0b0010),

	Parent(0b0100),

	Child(0b1000),

	;

	private int code;

	private OrdLevel(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
