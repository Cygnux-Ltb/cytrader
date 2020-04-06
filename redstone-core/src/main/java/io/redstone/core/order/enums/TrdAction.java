package io.redstone.core.order.enums;

public enum TrdAction {

	Invalid(-1),

	Open(1),

	Close(2),

	CloseToday(3),

	CloseYesterday(4),

	;

	private int code;

	private TrdAction(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
