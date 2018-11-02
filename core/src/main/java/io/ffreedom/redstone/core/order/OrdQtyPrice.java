package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.redstone.core.order.TradeList.Trade;

public class OrdQtyPrice {

	private double totalQty;
	private double filledQty;
	private double remainedQty;

	private double offerPrice;
	private double avgExecutionPrice;

	public OrdQtyPrice(double totalQty, double offerPrice) {
		super();
		this.totalQty = totalQty;
		this.filledQty = 0;
		this.remainedQty = totalQty;
		this.offerPrice = offerPrice;
	}

	public double getTotalQty() {
		return totalQty;
	}

	public double getFilledQty() {
		return filledQty;
	}

	public void setFilledQty(double filledQty) {
		this.filledQty = filledQty;
	}

	public double getRemainedQty() {
		return remainedQty;
	}

	public void setRemainedQty(double remainedQty) {
		this.remainedQty = remainedQty;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public double getAvgExecutionPrice() {
		return avgExecutionPrice;
	}

	public OrdQtyPrice calculationAvgExecutionPrice(TradeList trades) {
		if (!trades.isEmpty()) {
			MutableSortedSet<Trade> tradeSet = trades.getTradeSet();
			double totalPrice = tradeSet
					.sumOfDouble(trade -> DoubleUtil.multiply8(trade.getTradePrice(), trade.getTradeQty()));
			double totalQty = DoubleUtil.correction8(tradeSet.sumOfDouble(trade -> trade.getTradeQty()));
			if (totalQty > 0.0D)
				this.avgExecutionPrice = DoubleUtil.division(totalPrice, totalQty);
			return this;
		}
		return this;
	}

}
