package io.mercury.polaris.financial.instrument;

public abstract class AbstractInstrument implements Instrument {

	private int id;
	private String code;
	private Symbol symbol;
	private boolean isEnable;

	protected AbstractInstrument(int id, String code, Symbol symbol) {
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

}
