package io.redstone.core.account;

public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6421678546942382394L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
