package io.mercury.financial.instrument;

public enum PriceMultiplier {

	NONE {
		@Override
		public long longMultiplier() {
			return PriceSupporter.LONG_MULTIPLIER_NONE;
		}

		@Override
		public double doubleMultiplier() {
			return PriceSupporter.DOUBLE_MULTIPLIER_NONE;
		}

		@Override
		public long convertToLong(double price) {
			return (long) price;
		}

		@Override
		public double convertToDouble(long price) {
			return (double) price;
		}
	},

	TEN_THOUSAND {
		@Override
		public long longMultiplier() {
			return PriceSupporter.LONG_MULTIPLIER_TEN_THOUSAND;
		}

		@Override
		public double doubleMultiplier() {
			return PriceSupporter.DOUBLE_MULTIPLIER_TEN_THOUSAND;
		}

		@Override
		public long convertToLong(double price) {
			return PriceSupporter.priceToLong4(price);
		}

		@Override
		public double convertToDouble(long price) {
			return PriceSupporter.priceToDouble4(price);
		}
	},

	MILLION {
		@Override
		public long longMultiplier() {
			return PriceSupporter.LONG_MULTIPLIER_MILLION;
		}

		@Override
		public double doubleMultiplier() {
			return PriceSupporter.DOUBLE_MULTIPLIER_MILLION;
		}

		@Override
		public long convertToLong(double price) {
			return PriceSupporter.priceToLong6(price);
		}

		@Override
		public double convertToDouble(long price) {
			return PriceSupporter.priceToDouble6(price);
		}
	},

	BILLION {
		@Override
		public long longMultiplier() {
			return PriceSupporter.LONG_MULTIPLIER_BILLION;
		}

		@Override
		public double doubleMultiplier() {
			return PriceSupporter.DOUBLE_MULTIPLIER_BILLION;
		}

		@Override
		public long convertToLong(double price) {
			return PriceSupporter.priceToLong8(price);
		}

		@Override
		public double convertToDouble(long price) {
			return PriceSupporter.priceToDouble8(price);
		}
	},

	;

	private PriceMultiplier() {
	}

	public abstract long longMultiplier();

	public abstract double doubleMultiplier();

	public abstract long convertToLong(double price);

	public abstract double convertToDouble(long price);

	public static final class PriceSupporter {

		/**
		 * 
		 */
		public static final long LONG_MULTIPLIER_NONE = 1L;

		/**
		 * 
		 */
		public static final double DOUBLE_MULTIPLIER_NONE = 1.0D;

		/**
		 * 
		 */
		public static final long LONG_MULTIPLIER_TEN_THOUSAND = 10000L;

		/**
		 * 
		 */
		public static final double DOUBLE_MULTIPLIER_TEN_THOUSAND = 10000.0D;

		/**
		 * 
		 */
		public static final long LONG_MULTIPLIER_MILLION = 1000000L;

		/**
		 * 
		 */
		public static final double DOUBLE_MULTIPLIER_MILLION = 1000000.0D;

		/**
		 * 
		 */
		public static final long LONG_MULTIPLIER_BILLION = 100000000L;

		/**
		 * 
		 */
		public static final double DOUBLE_MULTIPLIER_BILLION = 100000000.0D;

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

	}

	public static void main(String[] args) {

		System.out.println(PriceSupporter.priceToLong8(4.981312));

		System.out.println(PriceSupporter.priceToDouble8(4981312));

	}

}
