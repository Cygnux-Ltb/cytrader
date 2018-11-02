package io.ffreedom.redstone.core.trade.enums;

public enum Action {

	Open(0),

	Close(1),

	;

	private int code;

	private Action(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
