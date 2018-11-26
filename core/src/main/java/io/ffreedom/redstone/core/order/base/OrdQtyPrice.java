package io.ffreedom.redstone.core.order.base;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.redstone.core.order.base.TradeList.Trade;

public class OrdQtyPrice {

	private double offerQty;
	private double filledQty;
	private double lastFilledQty;
	private double leavesQty;

	private double offerPrice;
	private double avgPrice;

	public OrdQtyPrice(double offerQty, double offerPrice) {
		if (offerPrice <= 0)
			throw new IllegalArgumentException("Param -> offerQty Must be greater than 0.");
		this.offerQty = offerQty;
		this.filledQty = 0;
		this.lastFilledQty = 0;
		this.leavesQty = offerQty;
		this.offerPrice = offerPrice;
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

	public OrdQtyPrice calculationAvgPrice(TradeList trades) {
		if (!trades.isEmpty()) {
			MutableSortedSet<Trade> tradeSet = trades.getTradeSet();
			double totalPrice = tradeSet
					.sumOfDouble(trade -> DoubleUtil.multiply8(trade.getTradePrice(), trade.getTradeQty()));
			double totalQty = DoubleUtil.correction8(tradeSet.sumOfDouble(trade -> trade.getTradeQty()));
			if (totalQty > 0.0D)
				this.avgPrice = DoubleUtil.division(totalPrice, totalQty);
			return this;
		}
		return this;
	}

}
