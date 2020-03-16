package io.redstone.core.order.impl;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.StopLoss;

public abstract class ActualOrder extends AbstractOrder {

	/**
	 * 所属策略订单
	 */
	private long strategyOrdId;

	/**
	 * 止损
	 */
	private StopLoss stopLoss;

	protected ActualOrder(long strategyOrdId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId) {
		this(strategyOrdId, instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId, null);
	}

	protected ActualOrder(long strategyOrdId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
		this.strategyOrdId = strategyOrdId;
		if (stopLoss == null)
			this.stopLoss = new StopLoss(ordSysId(), ordSide.direction());
		else
			this.stopLoss = stopLoss;
	}

	@Override
	public long strategyOrdId() {
		return strategyOrdId;
	}

	public abstract long parentId();

	public StopLoss stopLoss() {
		return stopLoss;
	}

}
