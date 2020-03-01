package io.redstone.adaptor.jctp.dto.inbound;

public class CtpInboundError {

	// 错误编码
	private int ErrorID;

	// 错误信息
	private String ErrorMsg;

	public int getErrorID() {
		return ErrorID;
	}

	public CtpInboundError setErrorID(int errorID) {
		ErrorID = errorID;
		return this;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public CtpInboundError setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
		return this;
	}

}
