package io.ffreedom.redstone.core.order.impl;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdSort;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.StopLoss;
import io.ffreedom.redstone.core.order.structure.TradeSet;

public final class ChildOrder extends ActualOrder {

	private long parentId;

	/**
	 * 子订单成交列表
	 */
	private TradeSet tradeSet;;

	private ChildOrder(long parentId, Instrument instrument, long offerQty, double offerPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, OrdQtyPrice.withOffer(offerQty, offerPrice), ordSide, ordType, strategyId, subAccountId,
				stopLoss);
		this.parentId = parentId;
		this.tradeSet = new TradeSet(getOrdSysId());
	}

	public static ChildOrder generateChildOrder(long parentOrdSysId, Instrument instrument, long offerQty,
			double offerPrice, OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		return new ChildOrder(parentOrdSysId, instrument, offerQty, offerPrice, ordSide, ordType, strategyId,
				subAccountId, stopLoss);
	}

	private ChildOrder(long parentId, Instrument instrument, OrdQtyPrice qtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, qtyPrice, ordSide, ordType, strategyId, subAccountId, stopLoss);
		this.parentId = parentId;
	}

	public static ChildOrder generateChildOrder(long parentOrdSysId, Instrument instrument, OrdQtyPrice qtyPrice,
			OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		return new ChildOrder(parentOrdSysId, instrument, qtyPrice, ordSide, ordType, strategyId, subAccountId,
				stopLoss);
	}

	@Override
	public OrdSort getSort() {
		return OrdSort.Child;
	}

	public long getParentId() {
		return parentId;
	}

	public TradeSet getTradeSet() {
		return tradeSet;
	}

}
