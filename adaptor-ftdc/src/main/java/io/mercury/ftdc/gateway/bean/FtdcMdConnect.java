package io.mercury.ftdc.gateway.bean;

public final class FtdcMdConnect {

	private boolean Available;

	public FtdcMdConnect(boolean Available) {
		this.Available = Available;
	}

	public boolean isAvailable() {
		return Available;
	}

}
