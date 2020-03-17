package io.mercury.polaris.financial.vector;

import java.time.ZonedDateTime;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.sequence.Serial;

public final class RandomTimeSerial implements Serial<RandomTimeSerial> {

	private ZonedDateTime timePoint;
	private long epochSecond;
	private long repeat;
	private long serialNumber;

	public static RandomTimeSerial with(ZonedDateTime dateTime) {
		if (dateTime == null)
			throw new IllegalArgumentException("dateTime cannot null");
		return new RandomTimeSerial(dateTime, 0);
	}

	public static RandomTimeSerial with(RandomTimeSerial other) {
		if (other == null)
			throw new IllegalArgumentException("other RandomTimeSerial cannot null");
		return new RandomTimeSerial(other.timePoint, other.repeat++);
	}

	private RandomTimeSerial(ZonedDateTime timePoint, long repeat) {
		this.timePoint = timePoint;
		this.repeat = repeat;
		initEpochSecond();
		initSerialNumber();
	}

	private void initEpochSecond() {
		this.epochSecond = timePoint.toEpochSecond();
	}

	private void initSerialNumber() {
		this.serialNumber = epochSecond * 10000L + repeat;
	}

	@Override
	public long serialNumber() {
		return serialNumber;
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

		RandomTimeSerial timeStarted0 = RandomTimeSerial.with(now);
		System.out.println(timeStarted0.timePoint());
		System.out.println(timeStarted0.epochSecond());
		System.out.println(timeStarted0.serialNumber());

		RandomTimeSerial timeStarted1 = RandomTimeSerial.with(timeStarted0);
		System.out.println(timeStarted1.timePoint());
		System.out.println(timeStarted1.epochSecond());
		System.out.println(timeStarted1.serialNumber());

		System.out.println(EpochTime.millis());
		System.out.println(EpochTime.seconds());
		System.out.println(Long.MAX_VALUE);
	}

}
