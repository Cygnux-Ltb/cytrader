package io.redstone.core.order;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.common.datetime.EpochTime;

/**
 * 
 * Generate规则<br>
 * A方案<br>
 * 1获取当前epoch秒<br>
 * 2如果是同一秒内生成的两个id, 则自增位加一<br>
 * 
 * B方案<br>
 * 1使用一个固定日期作为基准<br>
 * 2使一个较长的自增位<br>
 * 
 * C方案<br>
 * 
 * 
 * implement为方案A<br>
 * 
 * @author yellow013
 * @creation 2019年4月13日
 */
@NotThreadSafe
public final class OrdSysIdAllocator {

	private static final int maxLimitOwnerId = 920;

	/**
	 * 
	 * @param strategyId min value 1 max value 920
	 * @return
	 */
	public static long allocate(int strategyId) {
		if (strategyId < 0 || strategyId > maxLimitOwnerId)
			throw new RuntimeException("strategyId is illegal.");
		return generate(strategyId);
	}

	public static long allocateFromThird() {
		return generate(921);
	}

	private static volatile int increment;

	private static volatile long lastUseEpochSeconds;

	private static long generate(int highPos) {
		long nowEpochSeconds = EpochTime.seconds();
		if (nowEpochSeconds != lastUseEpochSeconds) {
			lastUseEpochSeconds = nowEpochSeconds;
			increment = 0;
		}
		return highPos * 10_000_000_000_000_000L + lastUseEpochSeconds * 1_000_000L + ++increment;
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println(9219999999999999999L);
		System.out.println(Long.MAX_VALUE);
		System.out.println("000" + EpochTime.seconds() + "000000");
		System.out.println(allocate(921));
		System.out.println("OrdSysId");

	}

}
