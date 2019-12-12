package io.redstone.core.order.structure;

import org.eclipse.collections.api.list.MutableList;

import io.polaris.financial.instrument.Instrument.MarketConstant;
import io.redstone.core.order.structure.TradeList.Trade;

/**
 * [offerPrice] & [avgPrice] fix use {@link MarketConstant#PriceMultiplier}
 */
public final class OrdPrice {

	/**
	 * 委托价格
	 */
	private long offerPrice;
	/**
	 * 成交均价
	 */
	private long avgPrice;
	/**
	 * 以最优价格
	 */
	private boolean isBestPrice;

	private OrdPrice(long offerPrice) {
		this.offerPrice = offerPrice;
	}

	private OrdPrice(boolean isBestPrice) {
		this.isBestPrice = isBestPrice;
	}

	public static OrdPrice withOffer(long offerPrice) {
		return new OrdPrice(offerPrice);
	}

	public static OrdPrice withBestPrice() {
		return new OrdPrice(true);
	}

	public double offerPrice() {
		return offerPrice;
	}

	public OrdPrice offerPrice(long offerPrice) {
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

	public OrdPrice calculateAvgPrice(TradeList tradeList) {
		if (!tradeList.isEmpty()) {
			MutableList<Trade> internalList = tradeList.internalList();
			//计算总成交金额
			long totalTurnover = internalList.sumOfLong(trade -> trade.tradePrice() * trade.tradeQty());
			//计算总成交量
			long totalQty = internalList.sumOfLong(trade -> trade.tradeQty());
			if (totalQty > 0L)
				this.avgPrice = totalTurnover / totalQty;
			return this;
		}
		return this;
	}

}
