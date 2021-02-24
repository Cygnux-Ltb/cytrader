package io.cygnus.engine.scheduler.base;

import io.mercury.common.codec.Envelope;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SchedulerDataType implements Envelope {

	MarketData(0),

	OrderReport(1),

	AdaptorEvent(2),

	;

	@Getter
	private final int code;

}
