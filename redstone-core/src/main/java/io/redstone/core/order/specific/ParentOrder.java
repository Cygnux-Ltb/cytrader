package io.redstone.core.order.specific;

import java.util.List;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;
import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdAction;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;

/**
 * 
 * 一个实际需要执行的订单, 在具体执行时可以被拆分为多个子订单
 * 
 * @author yellow013
 * @creation 2018年7月9日
 */
public final class ParentOrder extends ActualOrder {

	private MutableList<ChildOrder> childOrders;

	/**
	 * 
	 * @param instrument
	 * @param offerQty
	 * @param offerPrice
	 * @param ordSide
	 * @param ordType
	 * @param trdAction
	 * @param strategyId
	 * @param strategyOrdId
	 * @param subAccountId
	 * @param stopLoss
	 */
	public ParentOrder(Instrument instrument, long offerQty, long offerPrice, TrdDirection trdDirection, OrdType ordType,
			TrdAction trdAction, int strategyId, long strategyOrdId, int subAccountId) {
		super(strategyId, strategyOrdId, instrument, OrdQty.withOfferQty(offerQty), OrdPrice.withOffer(offerPrice),
				trdDirection, ordType, trdAction, subAccountId);
		this.childOrders = MutableLists.newFastList(8);
	}

	public ChildOrder toChildOrder() {
		ChildOrder childOrder = new ChildOrder(strategyId(), parentOrdId(), strategyOrdId(), instrument(), ordQty(),
				ordPrice(),  trdDirection(), ordType(), trdAction(), subAccountId());
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

	@Override
	public long parentOrdId() {
		return ordSysId();
	}

}
