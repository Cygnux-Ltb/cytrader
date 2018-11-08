package io.ffreedom.redstone.actor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class ApplicationState {

	public static final int MINIMUM_ID = 100;

	public static final int MAXIMUM_ID = 214;

	private static int APPLICATION_ID = 0;

	private AppStatus currentState;

	private ApplicationState() {
	}

	public static int getApplicationId() {
		return APPLICATION_ID;
	}

	public static void setApplicationId(int applicationId) {
		if (APPLICATION_ID == 0) {
			if (applicationId > 0 && applicationId < 215) {
				ApplicationState.APPLICATION_ID = applicationId;
			} else {
				throw new RuntimeException("ApplicationId is illegal.");
			}
		}
	}

	public AppStatus appStatus() {
		return currentState;
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

		// System.out.println(LocalDate.now().getDayOfYear());
		// System.out.println(LocalTime.now().toSecondOfDay());

	}

}
