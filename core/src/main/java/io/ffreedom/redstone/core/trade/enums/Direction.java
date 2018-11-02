package io.ffreedom.redstone.core.trade.enums;

public enum Direction {

	Long(0),

	Short(1),

	;

	private int code;

	private Direction(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
