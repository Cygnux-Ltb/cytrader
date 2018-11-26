package io.ffreedom.redstone.core.order.base;

import javax.annotation.concurrent.NotThreadSafe;

import io.ffreedom.common.datetime.EpochTime;

@NotThreadSafe
public final class OrdSysIdGenerate {

	private static int increment;

	private static long lastUseEpochSeconds;

	private static int maxLimitOwnerId = 921;

	/**
	 * 
	 * @param ownerId
	 *            max value 921
	 * @return
	 */
	public static long next(int ownerId) {
		if (ownerId < 1 || ownerId > maxLimitOwnerId)
			throw new RuntimeException("OwnerId is illegal.");
		long nowEpochSeconds = EpochTime.seconds();
		if (nowEpochSeconds != lastUseEpochSeconds) {
			lastUseEpochSeconds = nowEpochSeconds;
			increment = 0;
		}
		return ownerId * 10_000_000_000_000_000L + lastUseEpochSeconds * 1_000_000L + ++increment;
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println(9219999999999999999L);
		System.out.println(Long.MAX_VALUE);
		System.out.println("000" + EpochTime.seconds() + "000000");

		System.out.println("OrdSysId");

		for (int i = 0; i < 10000; i++) {
			Thread.sleep(1);
			System.out.println(OrdSysIdGenerate.next(100));
		}

	}

}
