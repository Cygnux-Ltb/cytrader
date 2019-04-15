package io.ffreedom.redstone.core.order.utils;

import javax.annotation.concurrent.NotThreadSafe;

import io.ffreedom.common.datetime.EpochTime;

/**
 * 
 * Generate规则<br>
 * A方案<br>
 * 1获取当前epoch秒<br>
 * 2如果是通一秒内生成的两个id, 则自增位加一<br>
 * 
 * B方案<br>
 * 1使用一个固定日期作为基准<br>
 * 2使一个较长的自增位<br>
 * 
 * C方案<br>
 * 
 * 
 * impl为方案A<br>
 * 
 * @author yellow013
 * @creation 2019年4月13日
 */
@NotThreadSafe
public final class OrdSysIdGenerate {

	private static int increment;

	private static long lastUseEpochSeconds;

	private static int maxLimitOwnerId = 921;

	/**
	 * 
	 * @param ownerId min value 100 max value 921
	 * @return
	 */
	public static long next(int ownerId) {
		if (ownerId < 100 || ownerId > maxLimitOwnerId)
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

		for (int i = 0; i < 1000000; i++) {

			System.out.println(OrdSysIdGenerate.next(100));
		}

	}

}
