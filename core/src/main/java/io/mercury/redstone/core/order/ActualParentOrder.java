package io.mercury.redstone.core.order;

import static io.mercury.redstone.core.order.OrderUniqueIds.allocateId;

import java.util.function.Function;

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
public final class ActualParentOrder extends ActualOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096106824571703291L;

	/**
	 * 所属子订单
	 */
	private MutableList<ActualChildOrder> childOrders;

	/**
	 * 
	 * @param strategyId   策略Id
	 * @param subAccountId 子账户Id
	 * @param accountId    实际账户Id
	 * @param instrument   交易标的
	 * @param offerQty     委托数量
	 * @param offerPrice   委托价格
	 * @param type         订单类型
	 * @param direction    交易方向
	 * @param action       交易动作
	 * @param ownerOrdId   所属上级订单Id
	 */
	ActualParentOrder(int strategyId, int subAccountId, int accountId, Instrument instrument, int offerQty,
			long offerPrice, OrdType type, TrdDirection direction, TrdAction action, long ownerOrdId) {
		super(allocateId(strategyId), strategyId, subAccountId, accountId, instrument, OrdQty.withOffer(offerQty),
				OrdPrice.withOffer(offerPrice), type, direction, action, ownerOrdId);
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
	 * @param type         订单类型
	 * @param direction    交易方向
	 * @param action       交易动作
	 */
	ActualParentOrder(int strategyId, int subAccountId, int accountId, Instrument instrument, int offerQty,
			long offerPrice, OrdType type, TrdDirection direction, TrdAction action) {
		this(strategyId, subAccountId, accountId, instrument, offerQty, offerPrice, type, direction, action, 0L);
	}

	/**
	 * 
	 * @return ChildOrder
	 */
	ActualChildOrder toChildOrder() {
		ActualChildOrder childOrder = new ActualChildOrder(strategyId(), accountId(), subAccountId(), instrument(),
				qty().offerQty(), price().offerPrice(), type(), direction(), action(), uniqueId());
		childOrders.add(childOrder);
		return childOrder;
	}

	/**
	 * 由外部传入拆分为多个订单的逻辑
	 * 
	 * @param splitFunc
	 * @return
	 */
	MutableList<ActualChildOrder> splitChildOrder(
			Function<ActualParentOrder, MutableList<ActualChildOrder>> splitFunc) {
		this.childOrders.addAll(splitFunc.apply(this));
		return this.childOrders;
	}

	/**
	 * 
	 * @return
	 */
	public MutableList<ActualChildOrder> getChildOrders() {
		return childOrders;
	}

	@Override
	public int ordLevel() {
		return 1;
	}

	private static final String ParentOrderText = "{} :: {}, ParentOrder : uniqueId==[{}], ownerUniqueId==[{}], "
			+ "status==[{}], direction==[{}], action==[{}], type==[{}], instrument -> {}, "
			+ "price -> {}, qty -> {}, timestamp -> {}";

	@Override
	public void writeLog(Logger log, String objName, String msg) {
		log.info(ParentOrderText, objName, msg, uniqueId(), ownerUniqueId(), status(), direction(), action(), type(),
				instrument(), price(), qty(), timestamp());
	}

}
