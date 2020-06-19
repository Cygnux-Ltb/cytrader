package io.mercury.financial.vector;

import java.time.ZonedDateTime;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;

public final class RandomTimeSerial implements Serial {

	private ZonedDateTime timePoint;
	private long epochSecond;
	private long repeat;
	private long serialId;

	public static RandomTimeSerial newWith(ZonedDateTime timePoint) {
		return new RandomTimeSerial(Assertor.nonNull(timePoint, "timePoint"), 0);
	}

	public static RandomTimeSerial newWith(RandomTimeSerial previous) {
		Assertor.nonNull(previous, "previous");
		return new RandomTimeSerial(previous.timePoint, previous.repeat + 1);
	}

	private RandomTimeSerial(ZonedDateTime timePoint, long repeat) {
		this.timePoint = timePoint;
		this.epochSecond = timePoint.toEpochSecond();
		this.repeat = repeat;
		initSerialId();
	}

	private void initSerialId() {
		this.serialId = epochSecond * 1000L + repeat;
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

		RandomTimeSerial timeStarted0 = RandomTimeSerial.newWith(now);
		System.out.println(timeStarted0.timePoint());
		System.out.println(timeStarted0.epochSecond());
		System.out.println(timeStarted0.serialId());

		RandomTimeSerial timeStarted1 = RandomTimeSerial.newWith(timeStarted0);
		System.out.println(timeStarted1.timePoint());
		System.out.println(timeStarted1.epochSecond());
		System.out.println(timeStarted1.serialId());

		System.out.println(EpochTime.millis());
		System.out.println(EpochTime.seconds());
		System.out.println(Long.MAX_VALUE);
	}

}
