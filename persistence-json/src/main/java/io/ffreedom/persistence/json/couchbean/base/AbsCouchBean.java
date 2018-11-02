package io.ffreedom.persistence.json.couchbean.base;

public class AbsCouchBean {

	private String _id;
	private String _rev;
	private String value;

	public String get_id() {
		return _id;
	}

	public AbsCouchBean set_id(String _id) {
		this._id = _id;
		return this;
	}

	public String get_rev() {
		return _rev;
	}

	public AbsCouchBean set_rev(String _rev) {
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
