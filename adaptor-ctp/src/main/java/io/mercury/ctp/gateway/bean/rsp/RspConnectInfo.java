package io.mercury.ftdc.gateway.bean.rsp;

public final class RspConnectInfo {

	private int FrontID;
	private int SessionID;
	private boolean IsAvailable;

	public int getFrontID() {
		return FrontID;
	}

	public int getSessionID() {
		return SessionID;
	}

	public boolean isIsAvailable() {
		return IsAvailable;
	}

	public RspConnectInfo setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public RspConnectInfo setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public RspConnectInfo setIsAvailable(boolean isAvailable) {
		IsAvailable = isAvailable;
		return this;
	}

}
