package io.mercury.financial.vector;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 指标时间周期
 * 
 * @author yellow013
 */
public enum TimePeriod {

	S1(ofSeconds(1)),

	S2(ofSeconds(2)),

	S3(ofSeconds(3)),

	S4(ofSeconds(4)),

	S5(ofSeconds(5)),

	S6(ofSeconds(6)),

	S10(ofSeconds(10)),

	S12(ofSeconds(12)),

	S15(ofSeconds(15)),

	S20(ofSeconds(20)),

	S30(ofSeconds(30)),

	M1(ofMinutes(1)),

	M2(ofMinutes(2)),

	M3(ofMinutes(3)),

	M4(ofMinutes(4)),

	M5(ofMinutes(5)),

	M6(ofMinutes(6)),

	M10(ofMinutes(10)),

	M12(ofMinutes(12)),

	M15(ofMinutes(15)),

	M20(ofMinutes(20)),

	M30(ofMinutes(30)),

	M60(ofMinutes(60)),

	;

	private Duration duration;
	private int seconds;

	private TimePeriod(Duration duration) {
		this.duration = duration;
		this.seconds = (int) duration.getSeconds();
	}

	public Duration duration() {
		return duration;
	}

	public int seconds() {
		return seconds;
	}

	public static void main(String[] args) {
		for (TimePeriod period : TimePeriod.values()) {
			System.out.println(period.seconds());
			LocalTime plusSeconds = LocalTime.of(21, 0, 0).plusSeconds(period.seconds());
			System.out.println(plusSeconds);
		}

	}

}