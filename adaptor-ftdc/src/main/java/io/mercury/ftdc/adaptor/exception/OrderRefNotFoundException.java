package io.mercury.ftdc.adaptor.exception;

public class OrderRefNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -74254388017422611L;

	public OrderRefNotFoundException(long uniqueId) {
		super("uniqueId -> [" + uniqueId + "] is not find orderRef.");
	}

	public OrderRefNotFoundException(String orderRef) {
		super("orderRef -> [" + orderRef + "] is not find orderSysId.");
	}

}
