package io.mercury.redstone.core.order.structure;

import javax.annotation.Nonnull;
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

	/**
	 * 初始化订单生成时间
	 */
	public static OrdTimestamp generate() {
		return new OrdTimestamp();
	}

	/**
	 * 补充发送时间
	 * 
	 * @return
	 */
	public OrdTimestamp fillSendingTime() {
		this.sendingTime = EpochTimestamp.now();
		return this;
	}

	/**
	 * 补充首次收到订单回报的时间
	 * 
	 * @return
	 */
	public OrdTimestamp fillFirstReportTime() {
		this.firstReportTime = EpochTimestamp.now();
		return this;
	}

	/**
	 * 补充最终完成时间
	 * 
	 * @return
	 */
	public OrdTimestamp fillFinishTime() {
		this.finishTime = EpochTimestamp.now();
		return this;
	}

	@Nonnull
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

}
