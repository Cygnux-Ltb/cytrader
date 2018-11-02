package io.ffreedom.redstone.core.order;

import io.ffreedom.common.datetime.EpochTimestamp;
import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdRank;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

public final class ChildOrder extends SingleOrder {

	public ChildOrder(Long parentOrderId, Instrument instrument, double quantity, double offerPrice, OrdSide ordSide,
			OrdType ordType, OrdStatus ordStatus, int strategyId, int subAccountId) {
		super(instrument, new OrdQtyPrice(quantity, offerPrice), ordSide, ordType, ordStatus,
				new OrdTimestamps(EpochTimestamp.now()), strategyId, subAccountId);
		this.parentOrderId = parentOrderId;
	}

	private long parentOrderId;

	public long getParentOrderId() {
		return parentOrderId;
	}

	@Override
	public OrdRank getOrdRank() {
		return OrdRank.Child;
	}

	public static void main(String[] args) {}

}
