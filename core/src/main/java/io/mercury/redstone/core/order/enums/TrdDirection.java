package io.mercury.redstone.core.order.enums;

public enum TrdDirection {

	Invalid(-1),

	Long(1),

	Short(2),

	;

	private int code;

	/**
	 * 
	 * @param code 代码
	 */
	private TrdDirection(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
