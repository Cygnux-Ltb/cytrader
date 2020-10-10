package io.mercury.financial.vector;

import java.time.Duration;
import java.time.ZonedDateTime;

import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;

/**
 * 时间周期序列
 * 
 * @author yellow013
 */
public final class TimePeriodSerial implements Serial {

	private long epochSecond;
	private Duration duration;
	private int seconds;;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;

	public static TimePeriodSerial newSerial(ZonedDateTime startTime, ZonedDateTime endTime, Duration duration) {
		Assertor.nonNull(startTime, "startTime");
		Assertor.nonNull(endTime, "endTime");
		return new TimePeriodSerial(startTime, endTime, duration);
	}

	private TimePeriodSerial(ZonedDateTime startTime, ZonedDateTime endTime, Duration duration) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.seconds = (int) duration.getSeconds();
		this.epochSecond = startTime.toEpochSecond();
	}

	@Override
	public long serialId() {
		return epochSecond;
	}

	public long epochSecond() {
		return epochSecond;
	}

	public Duration duration() {
		return duration;
	}

	public int seconds() {
		return seconds;
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
