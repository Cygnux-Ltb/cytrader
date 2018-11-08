package io.ffreedom.redstone.actor.temp;

import io.ffreedom.common.functional.Callback;

public abstract class Manager<IN> {

	private boolean isInitialized = false;

	abstract boolean initialization(Callback<IN> callback);

	public boolean isInitialized() {
		return isInitialized;
	}

	protected void initFinished() {
		this.isInitialized = true;
	}

}
