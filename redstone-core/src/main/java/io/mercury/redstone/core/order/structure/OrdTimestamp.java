package io.mercury.redstone.core.order.structure;

import javax.annotation.Nullable;

import io.mercury.common.datetime.EpochTimestamp;

public final class OrdTimestamp {

	private EpochTimestamp generateTime;
	private EpochTimestamp sendingTime;
	private EpochTimestamp firstReportTime;
	private EpochTimestamp finishTime;

	private OrdTimestamp() {
		this.generateTime = EpochTimestamp.now();
	}

	public static OrdTimestamp generate() {
		return new OrdTimestamp();
	}

	public EpochTimestamp generateTime() {
		return generateTime;
	}

	@Nullable
	public EpochTimestamp sendingTime() {
		return sendingTime;
	}

	@Nullable
	public EpochTimestamp firstReportTime() {
		return firstReportTime;
	}

	@Nullable
	public EpochTimestamp finishTime() {
		return finishTime;
	}

	public OrdTimestamp fillSendingTime() {
		this.sendingTime = EpochTimestamp.now();
		return this;
	}

	public OrdTimestamp fillFirstReportTime() {
		this.firstReportTime = EpochTimestamp.now();
		return this;
	}

	public OrdTimestamp fillFinishTime() {
		this.finishTime = EpochTimestamp.now();
		return this;
	}

}
