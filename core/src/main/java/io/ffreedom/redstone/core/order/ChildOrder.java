package io.ffreedom.redstone.core.order;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdSort;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;
import io.ffreedom.redstone.core.order.structure.StopLoss;

public final class ChildOrder extends ActualOrder {

	private long parentOrdSysId;

	private ChildOrder(long parentOrdSysId, Instrument instrument, double offerQty, double offerPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, OrdQtyPrice.withOffer(offerQty, offerPrice), ordSide, ordType, OrdStatus.PendingNew,
				OrdTimestamps.generate(), strategyId, subAccountId, stopLoss);
		this.parentOrdSysId = parentOrdSysId;
	}

	public static ChildOrder generateChildOrder(long parentOrdSysId, Instrument instrument, double offerQty,
			double offerPrice, OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		return new ChildOrder(parentOrdSysId, instrument, offerQty, offerPrice, ordSide, ordType, strategyId,
				subAccountId, stopLoss);
	}

	private ChildOrder(long parentOrdSysId, Instrument instrument, OrdQtyPrice qtyPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, qtyPrice, ordSide, ordType, OrdStatus.PendingNew, OrdTimestamps.generate(), strategyId,
				subAccountId, stopLoss);
		this.parentOrdSysId = parentOrdSysId;
	}

	public static ChildOrder generateChildOrder(long parentOrdSysId, Instrument instrument, OrdQtyPrice qtyPrice,
			OrdSide ordSide, OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		return new ChildOrder(parentOrdSysId, instrument, qtyPrice, ordSide, ordType, strategyId, subAccountId,
				stopLoss);
	}

	public long getParentOrdSysId() {
		return parentOrdSysId;
	}

	@Override
	public OrdSort getSort() {
		return OrdSort.Child;
	}

}
