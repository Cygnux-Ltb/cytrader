package io.ffreedom.redstone.core.order;

import io.ffreedom.common.datetime.EpochTimestamp;

public final class OrdTimestamps {

	private EpochTimestamp generateTime;
	private EpochTimestamp sendTime;
	private EpochTimestamp recvTime;
	private EpochTimestamp finishTime;

	public OrdTimestamps(EpochTimestamp generateTime) {
		super();
		this.generateTime = generateTime;
	}

	public EpochTimestamp getGenerateTime() {
		return generateTime;
	}

	public EpochTimestamp getSendTime() {
		return sendTime;
	}

	public EpochTimestamp getRecvTime() {
		return recvTime;
	}

	public EpochTimestamp getFinishTime() {
		return finishTime;
	}

	public OrdTimestamps setSendTime(EpochTimestamp sendTime) {
		this.sendTime = sendTime;
		return this;
	}

	public OrdTimestamps setRecvTime(EpochTimestamp recvTime) {
		this.recvTime = recvTime;
		return this;
	}

	public OrdTimestamps setFinishTime(EpochTimestamp finishTime) {
		this.finishTime = finishTime;
		return this;
	}

	

}
