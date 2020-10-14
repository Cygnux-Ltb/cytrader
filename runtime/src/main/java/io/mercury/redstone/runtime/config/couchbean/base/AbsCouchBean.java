package io.mercury.redstone.runtime.config.couchbean.base;

public class AbsCouchBean {

	private String _id;
	private String _rev;
	private String value;

	public String _id() {
		return _id;
	}

	public AbsCouchBean _id(String _id) {
		this._id = _id;
		return this;
	}

	public String _rev() {
		return _rev;
	}

	public AbsCouchBean _rev(String _rev) {
		this._rev = _rev;
		return this;
	}

	public String getValue() {
		return value;
	}

	public AbsCouchBean setValue(String value) {
		this.value = value;
		return this;
	}

}
