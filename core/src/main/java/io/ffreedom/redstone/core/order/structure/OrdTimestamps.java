package io.ffreedom.redstone.core.order.structure;

import io.ffreedom.common.datetime.EpochTimestamp;

public final class OrdTimestamps {

	private EpochTimestamp generateTime;
	private EpochTimestamp sendingTime;
	private EpochTimestamp finishTime;

	private OrdTimestamps() {
		this.generateTime = EpochTimestamp.now();
	}

	public static OrdTimestamps newTimestamp() {
		return new OrdTimestamps();
	}

	public EpochTimestamp getGenerateTime() {
		return generateTime;
	}

	public EpochTimestamp getSendingTime() {
		return sendingTime;
	}

	public EpochTimestamp getFinishTime() {
		return finishTime;
	}

	public OrdTimestamps fillingSendingTime() {
		this.sendingTime = EpochTimestamp.now();
		return this;
	}

	public OrdTimestamps fillingFinishTime() {
		this.finishTime = EpochTimestamp.now();
		return this;
	}

}
