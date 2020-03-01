package io.redstone.core.order.impl;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdSort;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.StopLoss;
import io.redstone.core.order.structure.TradeList;

/**
 * 最小的订单执行单元
 * 
 * @author yellow013
 * @creation 2018年1月14日
 */
public final class ChildOrder extends ActualOrder {

	private long parentId;

	/**
	 * 子订单成交列表
	 */
	private TradeList tradeList;

	private ChildOrder(long parentId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId, stopLoss);
		this.parentId = parentId;
		this.tradeList = new TradeList(ordSysId());
	}

	private ChildOrder(long parentId, Instrument instrument, long offerQty, long offerPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		this(parentId, instrument, OrdQty.withOffer(offerQty), OrdPrice.withOffer(offerPrice), ordSide, ordType,
				strategyId, subAccountId, stopLoss);
	}

	public static ChildOrder generateChildOrder(long parentOrdSysId, Instrument instrument, long offerQty,
			long offerPrice, OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		return new ChildOrder(parentOrdSysId, instrument, offerQty, offerPrice, ordSide, ordType, strategyId,
				subAccountId, stopLoss);
	}

	public static ChildOrder generateChildOrder(long parentOrdSysId, Instrument instrument, OrdQty ordQty,
			OrdPrice ordPrice, OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		return new ChildOrder(parentOrdSysId, instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId,
				stopLoss);
	}

	@Override
	public OrdSort ordSort() {
		return OrdSort.Child;
	}

	public long parentId() {
		return parentId;
	}

	public TradeList tradeList() {
		return tradeList;
	}

}
