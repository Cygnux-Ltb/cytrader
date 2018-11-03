package io.ffreedom.redstone.core.trade.enums;

public enum TradeAction {

	Invalid(-1),

	Open(0),

	Close(1),

	;

	private int code;

	private TradeAction(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
