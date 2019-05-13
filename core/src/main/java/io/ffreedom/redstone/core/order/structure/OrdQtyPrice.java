package io.ffreedom.redstone.core.order.structure;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.ffreedom.common.number.DoubleUtil;
import io.ffreedom.redstone.core.order.structure.TradeSet.Trade;

/**
 * @author yellow013
 *
 */
public class OrdQtyPrice {

	/**
	 * 委托数量
	 */
	private long offerQty;
	/**
	 * 剩余数量
	 */
	private long leavesQty;
	/**
	 * 已成交数量
	 */
	private long filledQty;
	/**
	 * 最后一次成交数量
	 */
	private long lastFilledQty;
	/**
	 * 是否以最大能成交的数量
	 */
	private boolean isMaxPossibleQty;

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

	private OrdQtyPrice(long offerQty, double offerPrice) {
		this.offerQty = offerQty;
		this.leavesQty = offerQty;
		this.offerPrice = offerPrice;
	}

	private OrdQtyPrice() {
	}

	public static OrdQtyPrice withOffer(long offerQty, double offerPrice) {
		return new OrdQtyPrice(offerQty, offerPrice);
	}

	public static OrdQtyPrice withBestPrice(long offerQty) {
		return new OrdQtyPrice().setBestPrice(true).setOfferQty(offerQty);
	}

	public static OrdQtyPrice withMaxPossibleQty(double offerPrice) {
		return new OrdQtyPrice().setMaxPossibleQty(true).setOfferPrice(offerPrice);
	}

	public static OrdQtyPrice withBestPriceAndMaxPossibleQty() {
		return new OrdQtyPrice().setBestPrice(true).setMaxPossibleQty(true);
	}

	public long getOfferQty() {
		return offerQty;
	}

	public long getLeavesQty() {
		return leavesQty;
	}

	public long getFilledQty() {
		return filledQty;
	}

	public long getLastFilledQty() {
		return lastFilledQty;
	}

	public boolean isMaxPossibleQty() {
		return isMaxPossibleQty;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public boolean isBestPrice() {
		return isBestPrice;
	}

	private OrdQtyPrice setBestPrice(boolean isBestPrice) {
		this.isBestPrice = isBestPrice;
		return this;
	}

	private OrdQtyPrice setMaxPossibleQty(boolean isMaxPossibleQty) {
		this.isMaxPossibleQty = isMaxPossibleQty;
		return this;
	}

	public OrdQtyPrice setOfferQty(long offerQty) {
		if (this.offerQty == 0) {
			this.offerQty = offerQty;
			this.leavesQty = offerQty;
		}
		return this;
	}

	public OrdQtyPrice setOfferPrice(double offerPrice) {
		if (this.offerPrice == 0)
			this.offerPrice = offerPrice;
		return this;
	}

	public OrdQtyPrice setLeavesQty(long leavesQty) {
		this.leavesQty = leavesQty;
		return this;
	}

	public OrdQtyPrice setFilledQty(long filledQty) {
		this.lastFilledQty = this.filledQty;
		this.filledQty = filledQty;
		return this;
	}

	public OrdQtyPrice calculationAvgPrice(TradeSet tradeSet) {
		if (!tradeSet.isEmpty()) {
			MutableSortedSet<Trade> innerSet = tradeSet.getInnerSet();
			double totalPrice = DoubleUtil.correction8(
					innerSet.sumOfDouble(trade -> DoubleUtil.correction8(trade.getTradePrice() * trade.getTradeQty())));
			long totalQty = innerSet.sumOfLong(trade -> trade.getTradeQty());
			if (totalQty > 0L)
				this.avgPrice = DoubleUtil.correction8(totalPrice / totalQty);
			return this;
		}
		return this;
	}

}
