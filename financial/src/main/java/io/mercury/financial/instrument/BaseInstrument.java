package io.mercury.financial.instrument;

public abstract class BaseInstrument implements Instrument {

	/**
	 * [int]类型唯一编码
	 */
	private final int id;
	/**
	 * 字符串唯一编码
	 */
	private final String code;
	/**
	 * Symbol
	 */
	private final Symbol symbol;

	/**
	 * 
	 * @param id
	 * @param code
	 * @param symbol
	 */
	protected BaseInstrument(int id, String code, Symbol symbol) {
		this.id = id;
		this.code = code;
		this.symbol = symbol;
	}

	private boolean isEnable;

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
