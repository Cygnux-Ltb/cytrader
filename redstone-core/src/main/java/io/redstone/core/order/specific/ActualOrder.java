package io.redstone.core.order.specific;

import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.OrderBaseImpl;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdAction;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;

abstract class ActualOrder extends OrderBaseImpl {

	/**
	 * 所属策略订单
	 */
	private long strategyOrdId;

	/**
	 * 
	 */
	private TrdAction trdAction;

	/**
	 * 
	 * @param instrument
	 * @param ordQty
	 * @param ordPrice
	 * @param ordSide
	 * @param ordType
	 * @param trdAction
	 * @param strategyId
	 * @param strategyOrdId
	 * @param subAccountId
	 * @param ordStopLoss
	 */
	protected ActualOrder(int strategyId, long strategyOrdId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice,
			TrdDirection trdDirection, OrdType ordType, TrdAction trdAction, int subAccountId) {
		super(strategyId, instrument, ordQty, ordPrice, trdDirection, ordType, subAccountId);
		this.strategyOrdId = strategyOrdId;
		this.trdAction = trdAction;
	}

	@Override
	public long strategyOrdId() {
		return strategyOrdId;
	}

	public TrdAction trdAction() {
		return trdAction;
	}

	public abstract long parentOrdId();

}
