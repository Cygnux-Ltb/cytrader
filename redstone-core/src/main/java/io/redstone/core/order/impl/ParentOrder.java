package io.redstone.core.order.impl;

import java.util.List;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;
import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.StopLoss;

/**
 * 
 * 一个实际需要执行的订单, 在具体执行时可以被拆分为多个子订单
 * 
 * @author yellow013
 * @creation 2018年7月9日
 */
public final class ParentOrder extends ActualOrder {

	private MutableList<ChildOrder> childOrders;
	private long virtualId;

	public ParentOrder(long virtualId, Instrument instrument, long offerQty, long offerPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, OrdQty.withOfferQty(offerQty), OrdPrice.withOffer(offerPrice), ordSide, ordType, strategyId,
				subAccountId, stopLoss);
		this.childOrders = MutableLists.newFastList(8);
		this.virtualId = virtualId;
	}

	public ChildOrder toChildOrder() {
		ChildOrder childOrder = ChildOrder.generateChildOrder(ordSysId(), instrument(), ordQty(), ordPrice(), ordSide(),
				ordType(), strategyId(), subAccountId(), stopLoss());
		childOrders.add(childOrder);
		return childOrder;
	}

	public List<ChildOrder> toChildOrder(int count) {
		// TODO 增加拆分为多个订单的逻辑
		OrdQty qty = ordQty();
		qty.offerQty();
		return this.childOrders;
	}

	@Override
	public OrdLevel ordLevel() {
		return OrdLevel.Parent;
	}

	public long getVirtualId() {
		return virtualId;
	}

	public long getParentId() {
		return ordSysId();
	}

}
