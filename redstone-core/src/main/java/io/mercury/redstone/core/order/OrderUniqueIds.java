package io.mercury.redstone.core.order;

import java.time.Instant;
import java.time.ZonedDateTime;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZone;
import io.mercury.common.util.BitOperator;
import io.mercury.redstone.core.strategy.Strategy.StrategyIdConst;

/**
 * 
 * Generate规则<br>
 * <br>
 * A方案<br>
 * 1.获取当前epoch秒<br>
 * 2.如果是同一秒内生成的两个id, 则自增位加一<br>
 * <br>
 * B方案<br>
 * 1.使用一个固定日期作为基准<br>
 * 2.使用一个较长的自增位<br>
 * <br>
 * C方案<br>
 * 1.使用位运算合并long类型, 分配64位<br>
 * 2.最高位使用strategyId <br>
 * <br>
 * 当前实现为方案A<br>
 * 
 * @author yellow013
 * @creation 2019年4月13日
 */
@NotThreadSafe
public final class OrderUniqueIds {

	/**
	 * 
	 * @param strategyId min value 1 max value 920
	 * @return
	 */
	public static long allocateId(int strategyId) {
		if (strategyId < 0 || strategyId > StrategyIdConst.MaxStrategyId)
			throw new RuntimeException("strategyId is illegal");
		return generate(strategyId);
	}

	public static long allocateIdForExternal() {
		return generate(StrategyIdConst.ExternalStrategyId);
	}

	private static volatile int increment;
	private static volatile long lastUseEpochSeconds;

	private static long generate(int highPos) {
		long epochSeconds = EpochTime.seconds();
		if (epochSeconds != lastUseEpochSeconds) {
			lastUseEpochSeconds = epochSeconds;
			increment = 0;
		}
		return highPos * 10_000_000_000_000_000L + lastUseEpochSeconds * 1_000_000L + ++increment;
	}

	/**
	 * 
	 */
	private static final long Baseline_2020_SECONDS = 1577836800L;
	private static final long Baseline_2020_MILLIS = Baseline_2020_SECONDS * TimeConst.MILLIS_PER_SECONDS;

	/**
	 * 
	 */
	private static final long Baseline_2010_SECONDS = 1262304000L;
	private static final long Baseline_2010_MILLIS = Baseline_2010_SECONDS * TimeConst.MILLIS_PER_SECONDS;

	/**
	 * 
	 */
	private static final long Baseline_2000_SECONDS = 946684800L;
	private static final long Baseline_2000_MILLIS = Baseline_2000_SECONDS * TimeConst.MILLIS_PER_SECONDS;

	public static int analyzeStrategyId(long uniqueId) {
		return 0;
	}

	public static long analyzeEpochSeconds(long uniqueId) {
		return 0;
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println(9219999999999999999L);
		System.out.println(Long.MAX_VALUE);
		System.out.println("000" + EpochTime.seconds() + "000000");
		System.out.println("000" + EpochTime.millis() + "000000");
		System.out.println(allocateId(800));
		System.out.println("uniqueId");
		System.out.println(BitOperator.longBinaryFormat(Long.MAX_VALUE));
		System.out.println(Short.MAX_VALUE);
		System.out.println(BitOperator.intBinaryFormat(Short.MAX_VALUE));
		System.out.println(BitOperator.intBinaryFormat(999));

		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.SIZE);
		System.out.println(Short.MAX_VALUE);
		System.out.println(Short.SIZE);

		System.out.println(24 * 60 * 60);

		System.out.println(Instant.ofEpochMilli(Baseline_2000_MILLIS));
		System.out.println(Instant.ofEpochMilli(Baseline_2010_MILLIS));
		System.out.println(Instant.ofEpochMilli(Baseline_2020_MILLIS));
		System.out.println(Instant.ofEpochSecond(Baseline_2000_SECONDS));
		System.out.println(Instant.ofEpochSecond(Baseline_2010_SECONDS));
		System.out.println(Instant.ofEpochSecond(Baseline_2020_SECONDS));

		System.out.println(ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, TimeZone.UTC).toEpochSecond());
		System.out.println(ZonedDateTime.of(2010, 1, 1, 0, 0, 0, 0, TimeZone.UTC).toEpochSecond());
		System.out.println(ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, TimeZone.UTC).toEpochSecond());

	}

}
