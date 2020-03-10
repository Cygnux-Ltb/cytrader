package io.mercury.polaris.financial.util;

public final class PriceUtil {

	/**
	 * 
	 */
	private static final long LongMultiplier4 = 10000L;

	/**
	 * 
	 */
	private static final double DoubleMultiplier4 = 10000.0D;

	/**
	 * 
	 */
	private static final long LongMultiplier6 = 1000000L;

	/**
	 * 
	 */
	private static final double DoubleMultiplier6 = 1000000.0D;

	/**
	 * 
	 */
	private static final long LongMultiplier8 = 100000000L;

	/**
	 * 
	 */
	private static final double DoubleMultiplier8 = 100000000.0D;

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static long priceToLong4(double price) {
		return (long) (price * LongMultiplier4);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static double priceToDouble4(long price) {
		return price / DoubleMultiplier4;
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static long priceToLong6(double price) {
		return (long) (price * LongMultiplier6);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static double priceToDouble6(long price) {
		return price / DoubleMultiplier6;
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static long priceToLong8(double price) {
		return (long) (price * LongMultiplier8);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static double priceToDouble8(long price) {
		return price / DoubleMultiplier8;
	}

	public static void main(String[] args) {

		System.out.println(PriceUtil.priceToLong8(4.981312));

		System.out.println(PriceUtil.priceToDouble8(4981312));

	}

}
