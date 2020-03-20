package io.mercury.financial.market.api;

public class QuoteLevelOverflowException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2602076635184902103L;

	public QuoteLevelOverflowException(String msg) {
		super(new ArrayIndexOutOfBoundsException(msg));
	}

}
