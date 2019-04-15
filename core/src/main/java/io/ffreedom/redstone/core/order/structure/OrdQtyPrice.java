package io.ffreedom.redstone.core.order.base;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.redstone.core.order.base.TradeSet.Trade;

/**
 * @author yellow013
 *
 */
public class OrdQtyPrice {

	/**
	 * 委托数量
	 */
	private double offerQty;
	/**
	 * 剩余数量
	 */
	private double leavesQty;
	/**
	 * 已成交数量
	 */
	private double filledQty;
	/**
	 * 最后一次成交数量
	 */
	private double lastFilledQty;
	/**
	 * 是否以最大可能的数量
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

	private OrdQtyPrice(double offerQty, double offerPrice) {
		this.offerQty = offerQty;
		this.leavesQty = offerQty;
		this.offerPrice = offerPrice;
	}

	private OrdQtyPrice() {

	}

	public static OrdQtyPrice withOffer(double offerQty, double offerPrice) {
		return new OrdQtyPrice(offerQty, offerPrice);
	}

	public static OrdQtyPrice withBestPrice(double offerQty) {
		return new OrdQtyPrice().isBestPrice(true).setOfferQty(offerQty);
	}

	public static OrdQtyPrice withMaxPossibleQty(double offerPrice) {
		return new OrdQtyPrice().isMaxPossibleQty(true).setOfferPrice(offerPrice);
	}

	public static OrdQtyPrice withBestPriceAndMaxPossibleQty() {
		return new OrdQtyPrice().isMaxPossibleQty(true).isMaxPossibleQty(true);
	}

	private OrdQtyPrice isBestPrice(boolean isBestPrice) {
		this.isBestPrice = isBestPrice;
		return this;
	}

	private OrdQtyPrice isMaxPossibleQty(boolean isMaxPossibleQty) {
		this.isMaxPossibleQty = isMaxPossibleQty;
		return this;
	}

	public boolean isBestPrice() {
		return isBestPrice;
	}

	public boolean isMaxPossibleQty() {
		return isMaxPossibleQty;
	}

	public OrdQtyPrice setOfferQty(double offerQty) {
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

	public double getOfferQty() {
		return offerQty;
	}

	public double getLastFilledQty() {
		return lastFilledQty;
	}

	public double getFilledQty() {
		return filledQty;
	}

	public double getLeavesQty() {
		return leavesQty;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public OrdQtyPrice setLeavesQty(double leavesQty) {
		this.leavesQty = leavesQty;
		return this;
	}

	public OrdQtyPrice setFilledQty(double filledQty) {
		this.lastFilledQty = this.filledQty;
		this.filledQty = filledQty;
		return this;
	}

	public OrdQtyPrice calculationAvgPrice(TradeSet tradeSet) {
		if (!tradeSet.isEmpty()) {
			MutableSortedSet<Trade> innerSet = tradeSet.innerSet();
			double totalPrice = innerSet
					.sumOfDouble(trade -> DoubleUtil.multiply8(trade.getTradePrice(), trade.getTradeQty()));
			double totalQty = DoubleUtil.correction8(innerSet.sumOfDouble(trade -> trade.getTradeQty()));
			if (totalQty > 0.0D)
				this.avgPrice = DoubleUtil.division(totalPrice, totalQty);
			return this;
		}
		return this;
	}

}
