package io.mercury.ftdc.gateway.bean;

public final class RspConnectInfo {

	private int FrontID;
	private int SessionID;
	private boolean Available;

	public int getFrontID() {
		return FrontID;
	}

	public int getSessionID() {
		return SessionID;
	}

	

	public boolean isAvailable() {
		return Available;
	}

	public RspConnectInfo setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public RspConnectInfo setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public RspConnectInfo setAvailable(boolean available) {
		Available = available;
		return this;
	}

	


}
