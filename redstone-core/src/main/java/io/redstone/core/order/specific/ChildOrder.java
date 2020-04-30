package io.redstone.core.order.specific;

import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdAction;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.TrdList;

/**
 * 实际执行订单的最小执行单元, 可能根据合规, 账户情况等由ParentOrder拆分而来
 * 
 * @author yellow013
 * @creation 2018年1月14日
 */
public final class ChildOrder extends ActualOrder {

	private long parentOrdId;

	/**
	 * 子订单成交列表
	 */
	private TrdList trdList;

	public ChildOrder(int strategyId, long strategyOrdId, long parentOrdId, Instrument instrument, OrdQty ordQty,
			OrdPrice ordPrice, TrdDirection trdDirection, OrdType ordType, TrdAction trdAction, int subAccountId) {
		super(strategyId, strategyOrdId, instrument, ordQty, ordPrice, trdDirection, ordType, trdAction, subAccountId);
		this.parentOrdId = parentOrdId;
		this.trdList = new TrdList(ordSysId());
	}

	@Override
	public OrdLevel ordLevel() {
		return OrdLevel.Child;
	}

	@Override
	public long parentOrdId() {
		return parentOrdId;
	}

	public TrdList trdList() {
		return trdList;
	}

}
