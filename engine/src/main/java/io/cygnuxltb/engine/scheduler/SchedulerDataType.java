package io.cygnuxltb.engine.scheduler;

import io.mercury.common.codec.Envelope;

public enum SchedulerDataType implements Envelope {

	MarketData(0),

	OrderReport(1),

	AdaptorReport(2),

	;

	private final int code;

	SchedulerDataType(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public int getVersion() {
		return 1;
	}

}
