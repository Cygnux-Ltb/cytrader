package io.redstone.core.order.impl;

import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.OrdStopLoss;
import io.redstone.core.order.structure.OrdTradeSet;

/**
 * 实际执行订单的最小执行单元, 可能根据合规, 账户情况等由ParentOrder拆分而来
 * 
 * @author yellow013
 * @creation 2018年1月14日
 */
public final class ChildOrder extends ActualOrder {

	private long parentId;

	/**
	 * 子订单成交列表
	 */
	private OrdTradeSet ordTradeSet;

	public ChildOrder(long parentId, long strategyOrdId, Instrument instrument, long offerQty, long offerPrice,
			OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, OrdStopLoss stopLoss) {
		this(parentId, strategyOrdId, instrument, OrdQty.withOfferQty(offerQty), OrdPrice.withOffer(offerPrice),
				ordSide, ordType, strategyId, subAccountId, stopLoss);
	}

	public ChildOrder(long parentId, long strategyOrdId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice,
			OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, OrdStopLoss stopLoss) {
		super(strategyOrdId, instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId, stopLoss);
		this.parentId = parentId;
		this.ordTradeSet = new OrdTradeSet(ordSysId());
	}

	@Override
	public OrdLevel ordLevel() {
		return OrdLevel.Child;
	}

	@Override
	public long parentId() {
		return parentId;
	}

	public OrdTradeSet ordTradeSet() {
		return ordTradeSet;
	}

}
