package io.redstone.core.exception;

public class AdaptorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7012414724771372952L;

	public AdaptorException(String message) {
		super(message);
	}

	public AdaptorException(Throwable throwable) {
		super(throwable);
	}

}
