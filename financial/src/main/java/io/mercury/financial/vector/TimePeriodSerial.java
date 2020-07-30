package io.mercury.financial.vector;

import java.time.ZonedDateTime;

import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;

public final class TimePeriodSerial implements Serial {

	private long epochSecond;
	private TimePeriod period;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;

	public static TimePeriodSerial newWith(ZonedDateTime startTime, ZonedDateTime endTime, TimePeriod period) {
		Assertor.nonNull(startTime, "startTime");
		Assertor.nonNull(endTime, "endTime");
		return new TimePeriodSerial(startTime, endTime, period);
	}

	private TimePeriodSerial(ZonedDateTime startTime, ZonedDateTime endTime, TimePeriod period) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.period = period;
		this.epochSecond = startTime.toEpochSecond();
	}

	@Override
	public long serialId() {
		return epochSecond;
	}

	public long epochSecond() {
		return epochSecond;
	}

	public TimePeriod period() {
		return period;
	}

	public ZonedDateTime startTime() {
		return startTime;
	}

	public ZonedDateTime endTime() {
		return endTime;
	}

	public boolean isPeriod(ZonedDateTime time) {
		return startTime.isBefore(time) && endTime.isAfter(time) ? true : false;
	}

	private String toStringCache;

	@Override
	public String toString() {
		if (toStringCache == null)
			toStringCache = epochSecond + " -> [" + startTime.getZone() + "][" + startTime.toLocalDateTime() + " - "
					+ endTime.toLocalDateTime() + "]";
		return toStringCache;
	}

}
