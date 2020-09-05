package io.mercury.financial.vector;

import java.time.ZonedDateTime;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;

public final class TimePointSerial implements Serial {

	private ZonedDateTime timePoint;
	private long epochSecond;
	private long repeat;
	private long serialId;

	/**
	 * 根据固定时间创建新序列
	 * 
	 * @param timePoint
	 * @return
	 */
	public static TimePointSerial newWith(ZonedDateTime timePoint) {
		Assertor.nonNull(timePoint, "timePoint");
		return new TimePointSerial(timePoint, 0);
	}

	/**
	 * 根据前一个序列创建新序列
	 * 
	 * @param previous
	 * @return
	 */
	public static TimePointSerial newWith(TimePointSerial previous) {
		Assertor.nonNull(previous, "previous");
		return new TimePointSerial(previous.timePoint, previous.repeat + 1);
	}

	private TimePointSerial(ZonedDateTime timePoint, long repeat) {
		this.timePoint = timePoint;
		this.epochSecond = timePoint.toEpochSecond();
		this.repeat = repeat;
		this.serialId = (epochSecond * 1000L) + repeat;
	}

	@Override
	public long serialId() {
		return serialId;
	}

	public ZonedDateTime timePoint() {
		return timePoint;
	}

	public long epochSecond() {
		return epochSecond;
	}

	public static void main(String[] args) {

		ZonedDateTime now = ZonedDateTime.now();

		long epochSecond = now.toEpochSecond();
		System.out.println(epochSecond);

		TimePointSerial timeStarted0 = TimePointSerial.newWith(now);
		System.out.println(timeStarted0.timePoint());
		System.out.println(timeStarted0.epochSecond());
		System.out.println(timeStarted0.serialId());

		TimePointSerial timeStarted1 = TimePointSerial.newWith(timeStarted0);
		System.out.println(timeStarted1.timePoint());
		System.out.println(timeStarted1.epochSecond());
		System.out.println(timeStarted1.serialId());

		System.out.println(EpochTime.millis());
		System.out.println(EpochTime.seconds());
		System.out.println(Long.MAX_VALUE);
	}

}
