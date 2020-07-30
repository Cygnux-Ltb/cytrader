package io.mercury.redstone.engine.actor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicReference;

public final class ApplicationStatus {

	private static final AtomicReference<AppStatus> CurrentStatus = new AtomicReference<AppStatus>(AppStatus.Unknown);

	private ApplicationStatus() {
	}

	public static AppStatus currentStatus() {
		return CurrentStatus.get();
	}

	public static void setInitialization() {
		CurrentStatus.set(AppStatus.Initialization);
	}

	public static void setOnline() {
		CurrentStatus.set(AppStatus.Online);
	}

	public static void setOffline() {
		CurrentStatus.set(AppStatus.Offline);
	}

	public static void setUnknown() {
		CurrentStatus.set(AppStatus.Unknown);
	}

	public static enum AppStatus {
		Initialization, Offline, Online, Unknown
	}

	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);

		long zoned2018_1 = ZonedDateTime.of(LocalDate.of(2018, 11, 8), LocalTime.MIN, ZoneId.systemDefault())
				.toInstant().getEpochSecond();

		System.out.println(zoned2018_1);

		long epochMilli = Instant.now().getEpochSecond();

		System.out.println(epochMilli);
		System.out.println(epochMilli - zoned2018_1);

	}

}
