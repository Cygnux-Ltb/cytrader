package io.mercury.financial.instrument;

abstract class BaseInstrument implements Instrument {

	/**
	 * int 类型唯一编码
	 */
	private int id;
	/**
	 * 字符串唯一编码
	 */
	private String code;
	private Symbol symbol;
	private boolean isEnable;

	BaseInstrument(int id, String code, Symbol symbol) {
		this.id = id;
		this.code = code;
		this.symbol = symbol;
	}

	@Override
	public void disable() {
		this.isEnable = false;
	}

	@Override
	public void enable() {
		this.isEnable = true;
	}

	@Override
	public boolean isDisabled() {
		return !isEnable;
	}

	@Override
	public boolean isEnabled() {
		return isEnable;
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public Symbol symbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return code;
	}

}
