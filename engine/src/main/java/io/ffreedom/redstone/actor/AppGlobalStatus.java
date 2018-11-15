package io.ffreedom.redstone.actor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class AppGlobalStatus {

	private int maxLimit = 20;

	private int appId = 0;

	private AppStatus currentStatus = AppStatus.Unknown;

	private final static AppGlobalStatus INSTANCE = new AppGlobalStatus();

	private AppGlobalStatus() {
	}

	public static int appId() {
		return INSTANCE.appId;
	}

	public static AppStatus appStatus() {
		return INSTANCE.currentStatus;
	}

	public static void setAppId(int appId) {
		INSTANCE.setAppId0(appId);
	}

	public void setAppId0(int appId) {
		if (this.appId == 0) {
			if (appId > 0 && appId < maxLimit) {
				this.appId = appId;
			} else {
				throw new RuntimeException("AppId is illegal.");
			}
		}
	}

	public static void setStatus(AppStatus status) {
		INSTANCE.currentStatus = status;
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
