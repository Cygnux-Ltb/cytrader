package io.cygnus.engine.config.couchbean.base;

public class AbsCouchBean {

	private String id;
	private String rev;
	private String value;

	public String id() {
		return id;
	}

	public AbsCouchBean id(String id) {
		this.id = id;
		return this;
	}

	public String rev() {
		return rev;
	}

	public AbsCouchBean rev(String rev) {
		this.rev = rev;
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
