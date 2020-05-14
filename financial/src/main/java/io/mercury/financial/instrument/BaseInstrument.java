package io.mercury.financial.instrument;

public abstract class BaseInstrument implements Instrument {

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

	protected BaseInstrument(int id, String code, Symbol symbol) {
		this.id = id;
		this.code = code;
		this.symbol = symbol;
	}

	@Override
	public Instrument disable() {
		this.isEnable = false;
		return this;
	}

	@Override
	public Instrument enable() {
		this.isEnable = true;
		return this;
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
