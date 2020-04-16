package io.mercury.ftdc.gateway.base;

public class FtdcRespException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4330136354361431411L;

	public FtdcRespException(String spiFunctionName, int errorId, String errorMsg) {
		super("SPI Function -> " + spiFunctionName + " | ErrorId -> " + errorId + " | ErrorMsg -> " + errorMsg);
	}

}
