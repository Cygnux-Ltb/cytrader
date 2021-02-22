package io.cygnus.restful.client.base;

public class PathParam {

	private String name;
	private String value;

	/**
	 * @param name
	 * @param value
	 */
	public PathParam(String name, String value) {
		this.name = '{' + name + '}';
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}