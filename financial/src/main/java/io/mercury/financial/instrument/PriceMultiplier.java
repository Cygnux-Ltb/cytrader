package io.mercury.financial.instrument;

import io.mercury.common.number.DecimalSupporter;

public enum PriceMultiplier {

	/**
	 * 1L
	 */
	NONE {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_1L;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_1D;
		}

		@Override
		public long toLong(double d) {
			return (long) d;
		}

		@Override
		public double toDouble(long l) {
			return (double) l;
		}
	},

	/**
	 * 100L
	 */
	HUNDRED {

		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_100L;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_100D;
		}

		@Override
		public long toLong(double d) {
			return DecimalSupporter.doubleToLong2(d);
		}

		@Override
		public double toDouble(long l) {
			return DecimalSupporter.longToDouble2(l);
		}
	},

	/**
	 * 10000L
	 */
	TEN_THOUSAND {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_10000L;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_10000D;
		}

		@Override
		public long toLong(double d) {
			return DecimalSupporter.doubleToLong4(d);
		}

		@Override
		public double toDouble(long l) {
			return DecimalSupporter.longToDouble4(l);
		}
	},

	/**
	 * 1000000L
	 */
	MILLION {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_1000000L;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_1000000D;
		}

		@Override
		public long toLong(double d) {
			return DecimalSupporter.doubleToLong6(d);
		}

		@Override
		public double toDouble(long l) {
			return DecimalSupporter.longToDouble6(l);
		}
	},

	/**
	 * 100000000L
	 */
	BILLION {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_100000000L;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_100000000D;
		}

		@Override
		public long toLong(double d) {
			return DecimalSupporter.doubleToLong8(d);
		}

		@Override
		public double toDouble(long l) {
			return DecimalSupporter.longToDouble8(l);
		}
	},

	;

	private PriceMultiplier() {
	}

	public abstract long longMultiplier();

	public abstract double doubleMultiplier();

	public abstract long toLong(double d);

	public abstract double toDouble(long l);

}
