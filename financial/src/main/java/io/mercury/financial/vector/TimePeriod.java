package io.mercury.financial.vector;

import java.time.Duration;

import io.mercury.common.util.Assertor;

/**
 * 指标时间周期
 * 
 * @author yellow013
 */
public final class TimePeriod {

	private Duration duration;
	private int seconds;
	private String name;

	private TimePeriod(Duration duration) {
		this.duration = duration;
		this.seconds = (int) duration.getSeconds();
		this.name = "TimePeriod:[Seconds==" + seconds + "]";
	}

	public static final TimePeriod newWith(Duration duration) {
		Assertor.nonNull(duration, "duration");
		return new TimePeriod(duration);
	}

	public Duration duration() {
		return duration;
	}

	public int seconds() {
		return seconds;
	}

	public String name() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		System.out.println(new TimePeriod(Duration.ofHours(1)));
	}

}