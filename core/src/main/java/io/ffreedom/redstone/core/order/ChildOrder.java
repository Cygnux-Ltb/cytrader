package io.ffreedom.redstone.core.order;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.base.ActualOrder;
import io.ffreedom.redstone.core.order.base.OrdQtyPrice;
import io.ffreedom.redstone.core.order.base.OrdTimestamps;
import io.ffreedom.redstone.core.order.enums.OrdRank;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

public final class ChildOrder extends ActualOrder {

	private long parentOrdSysId;

	public ChildOrder(Long parentOrdSysId, Instrument instrument, double offerQty, double offerPrice, OrdSide ordSide,
			OrdType ordType, OrdStatus ordStatus, int strategyId, int subAccountId) {
		super(instrument, OrdQtyPrice.withOffer(offerQty, offerPrice), ordSide, ordType, ordStatus, OrdTimestamps.generate(),
				strategyId, subAccountId);
		this.parentOrdSysId = parentOrdSysId;
	}

	public long getParentOrdSysId() {
		return parentOrdSysId;
	}

	@Override
	public OrdRank getRank() {
		return OrdRank.Child;
	}

}
