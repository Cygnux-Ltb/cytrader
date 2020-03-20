package io.mercury.financial.vector;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 指标时间周期
 * 
 * @author yellow013
 */
public enum TimePeriod {

	S1(Duration.ofSeconds(1)),

	S2(Duration.ofSeconds(2)),

	S3(Duration.ofSeconds(3)),

	S4(Duration.ofSeconds(4)),

	S5(Duration.ofSeconds(5)),

	S6(Duration.ofSeconds(6)),

	S10(Duration.ofSeconds(10)),

	S12(Duration.ofSeconds(12)),

	S15(Duration.ofSeconds(15)),

	S20(Duration.ofSeconds(20)),

	S30(Duration.ofSeconds(30)),

	M1(Duration.ofMinutes(1)),

	M2(Duration.ofMinutes(2)),

	M3(Duration.ofMinutes(3)),

	M4(Duration.ofMinutes(4)),

	M5(Duration.ofMinutes(5)),

	M6(Duration.ofMinutes(6)),

	M10(Duration.ofMinutes(10)),

	M12(Duration.ofMinutes(12)),

	M15(Duration.ofMinutes(15)),

	M20(Duration.ofMinutes(20)),

	M30(Duration.ofMinutes(30)),

	M60(Duration.ofMinutes(60)),

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