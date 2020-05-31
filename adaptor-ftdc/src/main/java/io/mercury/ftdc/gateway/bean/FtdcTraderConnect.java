package io.mercury.ftdc.gateway.bean;

public final class FtdcTraderConnect {

	private int FrontID;
	private int SessionID;
	private boolean Available;

	public FtdcTraderConnect(boolean Available) {
		this.Available = Available;
	}

	public int getFrontID() {
		return FrontID;
	}

	public int getSessionID() {
		return SessionID;
	}

	public boolean isAvailable() {
		return Available;
	}

	public FtdcTraderConnect setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public FtdcTraderConnect setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

}
