package io.mercury.ftdc.gateway.bean;

public final class FtdcRspInfo {

	/// 错误代码
	private int ErrorID;

	/// 错误信息
	private String ErrorMsg;

	public int getErrorID() {
		return ErrorID;
	}

	public FtdcRspInfo setErrorID(int errorID) {
		ErrorID = errorID;
		return this;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public FtdcRspInfo setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
		return this;
	}

}
