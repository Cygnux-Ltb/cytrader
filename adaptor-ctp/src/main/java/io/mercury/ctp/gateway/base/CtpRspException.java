package io.mercury.ctp.gateway.base;

public class CtpRspException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330136354361431411L;

	public CtpRspException(String methodName, int errorId, String errorMsg) {
		super("Method -> " + methodName + " | ErrorId -> " + errorId + " | ErrorMsg -> " + errorMsg);
	}

}
