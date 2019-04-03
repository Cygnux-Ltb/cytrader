package io.ffreedom.redstone.actor.temp;

import java.util.function.Consumer;

public abstract class Manager<IN> {

	private boolean isInitialized = false;

	abstract boolean initialization(Consumer<IN> callback);

	public boolean isInitialized() {
		return isInitialized;
	}

	protected void initFinished() {
		this.isInitialized = true;
	}

}
