package io.ffreedom.redstone.core.trade.enums;

public enum TradeDirection {

	Invalid(-1),

	Long(1),

	Short(2),

	;

	private int code;

	private TradeDirection(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
