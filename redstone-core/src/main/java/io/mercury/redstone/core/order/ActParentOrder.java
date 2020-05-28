package io.mercury.redstone.core.order;

import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.financial.instrument.Instrument;
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
public final class ActParentOrder extends ActualOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096106824571703291L;

	/**
	 * 所属子订单
	 */
	private MutableList<ActChildOrder> childOrders;

	/**
	 * 
	 * @param strategyId   策略Id
	 * @param accountId    实际账户Id
	 * @param subAccountId 子账户Id
	 * @param instrument   交易标的
	 * @param offerQty     委托数量
	 * @param offerPrice   委托价格
	 * @param ordType      订单类型
	 * @param direction    交易方向
	 * @param action       交易动作
	 * @param ownerOrdId   所属上级订单Id
	 */
	ActParentOrder(int strategyId, int accountId, int subAccountId, Instrument instrument, int offerQty,
			long offerPrice, OrdType ordType, TrdDirection direction, TrdAction action, long ownerOrdId) {
		super(strategyId, accountId, subAccountId, instrument, OrdQty.withOffer(offerQty),
				OrdPrice.withOffer(offerPrice), ordType, direction, action, ownerOrdId);
		this.childOrders = MutableLists.newFastList(8);
	}

	/**
	 * 
	 * @param strategyId   策略Id
	 * @param accountId    实际账户Id
	 * @param subAccountId 子账户Id
	 * @param instrument   交易标的
	 * @param offerQty     委托数量
	 * @param offerPrice   委托价格
	 * @param ordType      订单类型
	 * @param direction    交易方向
	 * @param action       交易动作
	 */
	ActParentOrder(int strategyId, int accountId, int subAccountId, Instrument instrument, int offerQty,
			long offerPrice, OrdType ordType, TrdDirection direction, TrdAction action) {
		this(strategyId, accountId, subAccountId, instrument, offerQty, offerPrice, ordType, direction, action, 0L);
	}

	/**
	 * 
	 * @return ChildOrder
	 */
	ActChildOrder toChildOrder() {
		ActChildOrder childOrder = new ActChildOrder(strategyId(), accountId(), subAccountId(), instrument(),
				ordQty().offerQty(), ordPrice().offerPrice(), ordType(), direction(), action(), ordSysId());
		childOrders.add(childOrder);
		return childOrder;
	}

	/**
	 * 
	 * @param count 数量
	 * @return
	 */
	MutableList<ActChildOrder> splitChildOrder(int count) {
		// TODO 增加拆分为多个订单的逻辑
		OrdQty qty = ordQty();
		qty.offerQty();
		return this.childOrders;
	}

	@Override
	public int ordLevel() {
		return 1;
	}

	private static final String ParentOrderOutputText = "{} :: {}, ParentOrder : ordSysId==[{}], ownerOrdId==[{}], "
			+ "ordStatus==[{}], direction==[{}], action==[{}], ordType==[{}], instrument -> {}, "
			+ "ordPrice -> {}, ordQty -> {}, ordTimestamps -> {}";

	@Override
	public void outputLog(Logger log, String objName, String msg) {
		log.info(ParentOrderOutputText, objName, msg, ordSysId(), ownerOrdId(), ordStatus(), direction(), action(),
				ordType(), instrument(), ordPrice(), ordQty(), ordTimestamp());
	}

}
