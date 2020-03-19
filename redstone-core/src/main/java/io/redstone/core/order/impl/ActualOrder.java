package io.redstone.core.order.impl;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.base.BaseOrder;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.OrdStopLoss;

public abstract class ActualOrder extends BaseOrder {

	/**
	 * 所属策略订单
	 */
	private long strategyOrdId;

	/**
	 * 止损
	 */
	private OrdStopLoss ordStopLoss;

	protected ActualOrder(long strategyOrdId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId) {
		this(strategyOrdId, instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId, null);
	}

	protected ActualOrder(long strategyOrdId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, OrdStopLoss ordStopLoss) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
		this.strategyOrdId = strategyOrdId;
		if (ordStopLoss == null)
			this.ordStopLoss = new OrdStopLoss(ordSysId(), ordSide.direction());
		else
			this.ordStopLoss = ordStopLoss;
	}

	@Override
	public long strategyOrdId() {
		return strategyOrdId;
	}

	public abstract long parentId();

	public OrdStopLoss ordStopLoss() {
		return ordStopLoss;
	}

}
