package io.mercury.financial.misc;

public enum PriceMultiplier {

	NONE {
		@Override
		public long longMultiplier() {
			return PriceMultiplierSupporter.LONG_MULTIPLIER_NONE;
		}

		@Override
		public double doubleMultiplier() {
			return PriceMultiplierSupporter.DOUBLE_MULTIPLIER_NONE;
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
			return PriceMultiplierSupporter.LONG_MULTIPLIER_TEN_THOUSAND;
		}

		@Override
		public double doubleMultiplier() {
			return PriceMultiplierSupporter.DOUBLE_MULTIPLIER_TEN_THOUSAND;
		}

		@Override
		public long convertToLong(double price) {
			return PriceMultiplierSupporter.priceToLong4(price);
		}

		@Override
		public double convertToDouble(long price) {
			return PriceMultiplierSupporter.priceToDouble4(price);
		}
	},

	MILLION {
		@Override
		public long longMultiplier() {
			return PriceMultiplierSupporter.LONG_MULTIPLIER_MILLION;
		}

		@Override
		public double doubleMultiplier() {
			return PriceMultiplierSupporter.DOUBLE_MULTIPLIER_MILLION;
		}

		@Override
		public long convertToLong(double price) {
			return PriceMultiplierSupporter.priceToLong6(price);
		}

		@Override
		public double convertToDouble(long price) {
			return PriceMultiplierSupporter.priceToDouble6(price);
		}
	},

	BILLION {
		@Override
		public long longMultiplier() {
			return PriceMultiplierSupporter.LONG_MULTIPLIER_BILLION;
		}

		@Override
		public double doubleMultiplier() {
			return PriceMultiplierSupporter.DOUBLE_MULTIPLIER_BILLION;
		}

		@Override
		public long convertToLong(double price) {
			return PriceMultiplierSupporter.priceToLong8(price);
		}

		@Override
		public double convertToDouble(long price) {
			return PriceMultiplierSupporter.priceToDouble8(price);
		}
	},

	;

	private PriceMultiplier() {
	}

	public abstract long longMultiplier();

	public abstract double doubleMultiplier();

	public abstract long convertToLong(double price);

	public abstract double convertToDouble(long price);

}
