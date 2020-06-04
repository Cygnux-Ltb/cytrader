package io.mercury.financial.instrument;

import io.mercury.common.util.StringUtil;

public abstract class AbsInstrument implements Instrument {

	/**
	 * 唯一编码
	 */
	private final int id;
	/**
	 * String唯一编码
	 */
	private final String code;

	/**
	 * 
	 * @param id
	 * @param code
	 * @param symbol
	 */
	protected AbsInstrument(int id, String code) {
		this.id = id;
		this.code = code;
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
	public String toString() {
		return code;
	}

	private String fmtText;

	@Override
	public String fmtText() {
		if (fmtText == null)
			this.fmtText = "{\"type\" : " + StringUtil.toText(type()) + ", \"id\" : " + id + "" + "}";
		return fmtText;
	}

}
