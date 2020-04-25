package io.mercury.financial.misc;

public final class PriceMultiplierSupporter {

	/**
	 * 
	 */
	static final long LONG_MULTIPLIER_NONE = 1L;

	/**
	 * 
	 */
	static final double DOUBLE_MULTIPLIER_NONE = 1.0D;

	/**
	 * 
	 */
	static final long LONG_MULTIPLIER_TEN_THOUSAND = 10000L;

	/**
	 * 
	 */
	static final double DOUBLE_MULTIPLIER_TEN_THOUSAND = 10000.0D;

	/**
	 * 
	 */
	static final long LONG_MULTIPLIER_MILLION = 1000000L;

	/**
	 * 
	 */
	static final double DOUBLE_MULTIPLIER_MILLION = 1000000.0D;

	/**
	 * 
	 */
	static final long LONG_MULTIPLIER_BILLION = 100000000L;

	/**
	 * 
	 */
	static final double DOUBLE_MULTIPLIER_BILLION = 100000000.0D;

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static long priceToLong4(double price) {
		return (long) (price * LONG_MULTIPLIER_TEN_THOUSAND);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static double priceToDouble4(long price) {
		return price / DOUBLE_MULTIPLIER_TEN_THOUSAND;
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static long priceToLong6(double price) {
		return (long) (price * LONG_MULTIPLIER_MILLION);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static double priceToDouble6(long price) {
		return price / DOUBLE_MULTIPLIER_MILLION;
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static long priceToLong8(double price) {
		return (long) (price * LONG_MULTIPLIER_BILLION);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	public final static double priceToDouble8(long price) {
		return price / DOUBLE_MULTIPLIER_BILLION;
	}

	public static void main(String[] args) {

		System.out.println(PriceMultiplierSupporter.priceToLong8(4.981312));

		System.out.println(PriceMultiplierSupporter.priceToDouble8(4981312));

	}

}
