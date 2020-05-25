package io.mercury.ftdc.adaptor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.datetime.TimeZone;

/**
 * 生成
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class OrderRefGenerator {

	private static final int maxLimitOwnerId = 213;

	private static final long epochSecondsBenchmarkPoint = ZonedDateTime
			.of(LocalDate.now().minusDays(1), LocalTime.of(19, 00), TimeZone.CST).toEpochSecond();

	private static long lastEpochSecondsDiff;

	private static int increment;

	public static int next(int ownerId) {
		if (ownerId < 1 || ownerId > maxLimitOwnerId)
			throw new IllegalArgumentException("ownerId is illegal.");
		long nowEpochSecondsDifference = EpochTime.seconds() - epochSecondsBenchmarkPoint;
		if (nowEpochSecondsDifference != lastEpochSecondsDiff) {
			lastEpochSecondsDiff = nowEpochSecondsDifference;
			increment = 0;
		}
		return ownerId * 100_000_000 + (int) lastEpochSecondsDiff * 1_000 + ++increment;
	}

	public static void main(String[] args) throws InterruptedException {

		LocalDate nowDate = LocalDate.now();
		LocalDate baseDate = nowDate.minusDays(1);

		System.out.println(nowDate);
		System.out.println(baseDate);

		ZonedDateTime nowDateTime = ZonedDateTime.of(nowDate, LocalTime.of(15, 00, 00), TimeZone.SYS_DEFAULT);
		ZonedDateTime baseDateTime = ZonedDateTime.of(baseDate, LocalTime.of(19, 00), TimeZone.SYS_DEFAULT);

		System.out.println(nowDateTime);
		System.out.println(baseDateTime);

		Instant nowInstant = nowDateTime.toInstant();
		Instant baseInstant = baseDateTime.toInstant();

		long diff = nowInstant.getEpochSecond() - baseInstant.getEpochSecond();

		System.out.println(Integer.MAX_VALUE);
		System.out.println(diff);

		for (int i = 0; i < 10240; i++) {
			Thread.sleep(5);
			System.out.println(OrderRefGenerator.next(5));
		}

		System.out.println(Instant.now().toEpochMilli());
		System.out.println(System.currentTimeMillis());

		System.out.println(EpochTime.seconds());
		ZonedDateTime of = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());

		System.out.println(of.toEpochSecond());

	}

}
