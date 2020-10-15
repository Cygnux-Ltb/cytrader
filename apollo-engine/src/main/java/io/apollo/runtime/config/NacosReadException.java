package io.apollo.runtime.config;

public final class NacosReadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866898838811364365L;

	public NacosReadException(String message) {
		super(message);
	}

	public NacosReadException(Exception e) {
		super(e.getMessage(), e);
	}

	public NacosReadException(String message, Exception e) {
		super(message, e);
	}

}
