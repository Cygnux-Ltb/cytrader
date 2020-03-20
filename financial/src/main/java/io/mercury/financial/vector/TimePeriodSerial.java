package io.mercury.financial.vector;

import java.time.ZonedDateTime;

import io.mercury.common.sequence.Serial;

public final class TimePeriodSerial implements Serial<TimePeriodSerial> {

	private long epochSecond;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;

	public static TimePeriodSerial with(ZonedDateTime startTime, ZonedDateTime endTime) {
		if (startTime == null)
			throw new IllegalArgumentException("startTime cannot null");
		if (endTime == null)
			throw new IllegalArgumentException("endTime cannot null");
		return new TimePeriodSerial(startTime, endTime);
	}

	private TimePeriodSerial(ZonedDateTime startTime, ZonedDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		initEpochSecond();
	}

	private void initEpochSecond() {
		this.epochSecond = startTime.toEpochSecond();
	}

	@Override
	public long serialNumber() {
		return epochSecond;
	}

	public long epochSecond() {
		return epochSecond;
	}

	public boolean isPeriod(ZonedDateTime time) {
		return startTime.isBefore(time) && endTime.isAfter(time) ? true : false;
	}

	public ZonedDateTime startTime() {
		return startTime;
	}

	public ZonedDateTime endTime() {
		return endTime;
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
