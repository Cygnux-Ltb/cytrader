package io.ffreedom.redstone.core.order;

import java.util.List;

import org.eclipse.collections.api.list.MutableList;

import io.ffreedom.common.collect.ECFactory;
import io.ffreedom.common.datetime.EpochTimestamp;
import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdRank;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

public final class ParentOrder extends SingleOrder {

	private MutableList<ChildOrder> childOrders;

	public ParentOrder(Instrument instrument, double quantity, double offerPrice, OrdSide ordSide, OrdType ordType,
			OrdStatus ordStatus, int strategyId, int subAccountId) {
		super(instrument, new OrdQtyPrice(quantity, offerPrice), ordSide, ordType, ordStatus,
				new OrdTimestamps(EpochTimestamp.now()), strategyId, subAccountId);
		// TODO List Type
		this.childOrders = ECFactory.newFastList();
	}

	public ChildOrder toChildOrder() {
		ChildOrder childOrder = new ChildOrder(ordSysId, instrument, ordQtyPrice.getTotalQty(),
				ordQtyPrice.getOfferPrice(), ordSide, ordType, ordStatus, strategyId, subAccountId);
		childOrders.add(childOrder);
		return childOrder;
	}

	public List<ChildOrder> toChildOrder(int count) {
		// TODO 增加拆分为多个订单的逻辑
		toChildOrder();
		return this.childOrders;
	}

	@Override
	public OrdRank getOrdRank() {
		return OrdRank.Parent;
	}

}
