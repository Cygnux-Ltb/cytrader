package io.redstone.core.order.structure;

import static io.mercury.common.number.DoubleArithmetic.correction8;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.redstone.core.order.structure.TradeSet.Trade;

public final class OrdPrice {

	/**
	 * 委托价格
	 */
	private double offerPrice;
	/**
	 * 成交均价
	 */
	private double avgPrice;
	/**
	 * 以最优价格
	 */
	private boolean isBestPrice;

	private OrdPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	private OrdPrice(boolean isBestPrice) {
		this.isBestPrice = isBestPrice;
	}

	public static OrdPrice withOffer(double offerPrice) {
		return new OrdPrice(offerPrice);
	}

	public static OrdPrice withBestPrice() {
		return new OrdPrice(true);
	}

	public double offerPrice() {
		return offerPrice;
	}

	public OrdPrice offerPrice(double offerPrice) {
		if (this.offerPrice == 0)
			this.offerPrice = offerPrice;
		return this;
	}

	public double avgPrice() {
		return avgPrice;
	}

	public boolean isBestPrice() {
		return isBestPrice;
	}

	public OrdPrice calculateAvgPrice(TradeSet tradeSet) {
		if (!tradeSet.isEmpty()) {
			MutableSortedSet<Trade> internalSet = tradeSet.internalSet();
			double totalPrice = correction8(
					internalSet.sumOfDouble(trade -> correction8(trade.getTradePrice() * trade.getTradeQty())));
			long totalQty = internalSet.sumOfLong(trade -> trade.getTradeQty());
			if (totalQty > 0L)
				this.avgPrice = correction8(totalPrice / totalQty);
			return this;
		}
		return this;
	}

}
