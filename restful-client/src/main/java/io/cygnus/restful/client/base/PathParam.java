package io.cygnus.restful.client.base;

import org.apache.commons.lang3.tuple.Pair;

public class PathParam extends Pair<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4350808306842027275L;

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

	@Override
	public String setValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRight() {
		// TODO Auto-generated method stub
		return null;
	}

}