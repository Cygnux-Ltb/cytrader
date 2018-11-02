package io.ffreedom.redstone.adaptor.ctp;

class CtpOrderRefNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -74254388017422611L;

	public CtpOrderRefNotFoundException(Long orderSysId) {
		super("orderSysId -> " + orderSysId + " is not find orderRef.");
	}

	public CtpOrderRefNotFoundException(Integer orderRef) {
		super("orderRef -> " + orderRef + " is not find orderSysId.");
	}

}
