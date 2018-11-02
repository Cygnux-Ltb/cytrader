package io.ffreedom.redstone.core.order;

import java.time.Clock;

public final class OrdSysIdGenerate {

	private static int increment;

	private static long lastUseEpochSecond;

	/**
	 * 
	 * @param ownerId
	 *            max value 921
	 * @return
	 */
	public static long nextOrdSysId(int ownerId) {
		long nowEpochSecond = Clock.systemUTC().instant().getEpochSecond();
		if (nowEpochSecond != lastUseEpochSecond) {
			lastUseEpochSecond = nowEpochSecond;
			increment = 0;
		}
		return ownerId * 10_000_000_000_000_000L + lastUseEpochSecond * 1_000_000L + ++increment;
	}

	public static void main(String[] args) {

		System.out.println(9219999999999999999L);
		System.out.println(Long.MAX_VALUE);
		System.out.println("000" + Clock.systemUTC().instant().getEpochSecond() + "000000");

		System.out.println("OrdSysId");

		for (int i = 0; i < 10000; i++)
			System.out.println(OrdSysIdGenerate.nextOrdSysId(100));

	}

}
