package io.redstone.core.order.utils;

public final class PriceUtil {

	private static final long longMultiplier = 100000000L;

	private static final double doubleMultiplier = 100000000.0D;

	public final static long doublePriceToLong(double price) {
		return (long) (price * longMultiplier);
	}

	public final static double longPriceToDouble(long price) {
		return price / doubleMultiplier;
	}
	
	
	
	public static void main(String[] args) {
		
		System.out.println(PriceUtil.doublePriceToLong(4.981312));
		
		System.out.println(PriceUtil.longPriceToDouble(4981312));
		
	}

}
