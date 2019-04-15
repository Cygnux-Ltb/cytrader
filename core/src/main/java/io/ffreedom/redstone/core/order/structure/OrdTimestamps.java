package io.ffreedom.redstone.core.order.structure;

import io.ffreedom.common.datetime.EpochTimestamp;

public final class OrdTimestamps {

	private EpochTimestamp generateTime;
	private EpochTimestamp sendTime;
	private EpochTimestamp finishTime;

	private OrdTimestamps() {
		this.generateTime = EpochTimestamp.now();
	}

	public static OrdTimestamps generate() {
		return new OrdTimestamps();
	}

	public EpochTimestamp getGenerateTime() {
		return generateTime;
	}

	public EpochTimestamp getSendTime() {
		return sendTime;
	}

	public EpochTimestamp getFinishTime() {
		return finishTime;
	}

	public OrdTimestamps fillingSendTime() {
		this.sendTime = EpochTimestamp.now();
		return this;
	}

	public OrdTimestamps fillingFinishTime() {
		this.finishTime = EpochTimestamp.now();
		return this;
	}

}
