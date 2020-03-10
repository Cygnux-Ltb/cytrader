package io.redstone.core.trade.enums;

public enum TrdAction {

	Invalid(-1),

	Open(1),

	Close(2),

	;

	private int code;

	private TrdAction(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
