package io.mercury.redstone.core.order.specific;

import java.util.List;

import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.OrderOutputText;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;

/**
 * 
 * 一个实际需要执行的订单, 在具体执行时可以被拆分为多个子订单
 * 
 * @author yellow013
 * @creation 2018年7月9日
 */
public final class ParentOrder extends ActualOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096106824571703291L;

	/**
	 * 所属子订单
	 */
	private MutableList<ChildOrder> childOrders;

	/**
	 * 
	 * @param strategyId
	 * @param subAccountId
	 * @param instrument
	 * @param offerQty
	 * @param offerPrice
	 * @param ordType
	 * @param direction
	 * @param action
	 * @param ownerOrdId
	 */
	public ParentOrder(int strategyId, int subAccountId, Instrument instrument, int offerQty, long offerPrice,
			OrdType ordType, TrdDirection direction, TrdAction action, long ownerOrdId) {
		super(strategyId, subAccountId, instrument, OrdQty.withOffer(offerQty), OrdPrice.withOffer(offerPrice), ordType,
				direction, action, ownerOrdId);
		this.childOrders = MutableLists.newFastList(8);
	}

	/**
	 * 
	 * @param strategyId
	 * @param subAccountId
	 * @param instrument
	 * @param offerQty
	 * @param offerPrice
	 * @param ordType
	 * @param direction
	 * @param action
	 */
	public ParentOrder(int strategyId, int subAccountId, Instrument instrument, int offerQty, long offerPrice,
			OrdType ordType, TrdDirection direction, TrdAction action) {
		this(strategyId, subAccountId, instrument, offerQty, offerPrice, ordType, direction, action, 0L);
	}

	/**
	 * 
	 * @return ChildOrder
	 */
	public ChildOrder toChildOrder() {
		ChildOrder childOrder = new ChildOrder(strategyId(), subAccountId(), instrument(), ordQty(), ordPrice(),
				ordType(), direction(), action(), ordSysId());
		childOrders.add(childOrder);
		return childOrder;
	}

	/**
	 * 
	 * @param count 数量
	 * @return
	 */
	public List<ChildOrder> splitChildOrder(int count) {
		// TODO 增加拆分为多个订单的逻辑
		OrdQty qty = ordQty();
		qty.offerQty();
		return this.childOrders;
	}

	@Override
	public int ordLevel() {
		return 1;
	}

	@Override
	public void outputLog(Logger log, String objName, String msg) {
		log.info(OrderOutputText.ParentOrderOutputText, objName, msg, ordSysId(), ownerOrdId(), ordStatus(),
				direction(), action(), ordType(), instrument(), ordPrice(), ordQty(), ordTimestamp());
	}

}
