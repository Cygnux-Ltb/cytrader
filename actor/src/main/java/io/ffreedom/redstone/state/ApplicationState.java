package io.ffreedom.redstone.state;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ApplicationState {

	public static final int MINIMUM_ID = 100;

	public static final int MAXIMUM_ID = 214;

	private static int APPLICATION_ID = 0;

	private AppState currentState;

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

	public AppState getCurrentState() {
		return currentState;
	}
	
	public static enum AppState {
		Initialization, Offline, Online, Unknown
	}

	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);

		System.out.println(LocalDate.now().getDayOfYear());
		System.out.println(LocalTime.now().toSecondOfDay());

		Map<String, String> shortIds = ZoneId.SHORT_IDS;

		System.out.println(ZoneId.of("Asia/Shanghai").getRules());

		Set<Entry<String, String>> entrySet = shortIds.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}

		ZoneOffset.getAvailableZoneIds().forEach(str -> {
			System.out.println(str);
		});
		;

	}

}
