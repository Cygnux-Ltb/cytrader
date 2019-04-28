package io.ffreedom.redstone.core.order.impl;

import java.util.List;

import org.eclipse.collections.api.list.MutableList;

import io.ffreedom.common.collect.MutableLists;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdSort;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.StopLoss;

public final class ParentOrder extends ActualOrder {

	private MutableList<ChildOrder> childOrders;

	public ParentOrder(Instrument instrument, long offerQty, double offerPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, OrdQtyPrice.withOffer(offerQty, offerPrice), ordSide, ordType, strategyId, subAccountId,
				stopLoss);
		this.childOrders = MutableLists.newFastList(8);
	}

	public ChildOrder toChildOrder() {
		ChildOrder childOrder = ChildOrder.generateChildOrder(getOrdSysId(), getInstrument(), getQtyPrice(), getSide(),
				getType(), getStrategyId(), getSubAccountId(), getStopLoss());
		childOrders.add(childOrder);
		return childOrder;
	}

	public List<ChildOrder> toChildOrder(int count) {
		// TODO 增加拆分为多个订单的逻辑
		OrdQtyPrice qtyPrice = getQtyPrice();
		qtyPrice.getOfferQty();
		return this.childOrders;
	}

	@Override
	public OrdSort getSort() {
		return OrdSort.Parent;
	}

}
