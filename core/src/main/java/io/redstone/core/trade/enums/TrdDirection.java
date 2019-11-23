package io.ffreedom.redstone.core.trade.enums;

public enum TrdDirection {

	Invalid(-1),

	Long(1),

	Short(2),

	;

	private int code;

	private TrdDirection(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
