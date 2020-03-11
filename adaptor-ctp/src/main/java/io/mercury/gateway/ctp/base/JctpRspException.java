package io.mercury.gateway.ctp.base;

public class JctpRspException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330136354361431411L;

	public JctpRspException(String methodName, int errorId, String errorMsg) {
		super("Method -> " + methodName + " | ErrorId -> " + errorId + " | ErrorMsg -> " + errorMsg);
	}

}
