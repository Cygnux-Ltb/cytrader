package io.mercury.financial.instrument;

import io.mercury.common.number.DecimalSupporter;

public enum PriceMultiplier {

	NONE {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_1;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_1;
		}

		@Override
		public long toLong(double price) {
			return (long) price;
		}

		@Override
		public double toDouble(long price) {
			return (double) price;
		}
	},

	HUNDRED {

		@Override
		public long longMultiplier() {

			return DecimalSupporter.LONG_MULTIPLIER_100;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_100;
		}

		@Override
		public long toLong(double price) {
			return DecimalSupporter.doubleToLong2(price);
		}

		@Override
		public double toDouble(long price) {
			return DecimalSupporter.longToDouble2(price);
		}

	},

	TEN_THOUSAND {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_10000;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_10000;
		}

		@Override
		public long toLong(double price) {
			return DecimalSupporter.doubleToLong4(price);
		}

		@Override
		public double toDouble(long price) {
			return DecimalSupporter.longToDouble4(price);
		}
	},

	MILLION {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_1000000;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_1000000;
		}

		@Override
		public long toLong(double price) {
			return DecimalSupporter.doubleToLong6(price);
		}

		@Override
		public double toDouble(long price) {
			return DecimalSupporter.longToDouble6(price);
		}
	},

	BILLION {
		@Override
		public long longMultiplier() {
			return DecimalSupporter.LONG_MULTIPLIER_100000000;
		}

		@Override
		public double doubleMultiplier() {
			return DecimalSupporter.DOUBLE_MULTIPLIER_100000000;
		}

		@Override
		public long toLong(double price) {
			return DecimalSupporter.doubleToLong8(price);
		}

		@Override
		public double toDouble(long price) {
			return DecimalSupporter.longToDouble8(price);
		}
	},

	;

	private PriceMultiplier() {
	}

	public abstract long longMultiplier();

	public abstract double doubleMultiplier();

	public abstract long toLong(double price);

	public abstract double toDouble(long price);

}
