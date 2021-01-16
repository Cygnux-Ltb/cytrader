package io.cygnus.exchange.core.utils;

import io.cygnus.exchange.core.common.CoreSymbolSpecification;

public final class BizArithmeticUtils {

	public static long calculateAmountAsk(long size, CoreSymbolSpecification spec) {
		return size * spec.baseScaleK;
	}

	public static long calculateAmountBid(long size, long price, CoreSymbolSpecification spec) {
		return size * (price * spec.quoteScaleK);
	}

	public static long calculateAmountBidTakerFee(long size, long price, CoreSymbolSpecification spec) {
		return size * (price * spec.quoteScaleK + spec.takerFee);
	}

	public static long calculateAmountBidReleaseCorrMaker(long size, long priceDiff, CoreSymbolSpecification spec) {
		return size * (priceDiff * spec.quoteScaleK + (spec.takerFee - spec.makerFee));
	}

	public static long calculateAmountBidTakerFeeForBudget(long size, long budgetInSteps,
			CoreSymbolSpecification spec) {
		return budgetInSteps * spec.quoteScaleK + size * spec.takerFee;
	}

}
