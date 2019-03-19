package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.base.OrdQtyPrice;
import io.ffreedom.redstone.core.order.base.OrdTimestamps;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

/**
 * 用于记录策略在出现交易信号后希望进行的交易
 * 
 * @author yellow013
 */
public class VirtualOrder {

	protected long ordSysId;
	protected Instrument instrument;
	protected OrdQtyPrice ordQtyPrice;
	protected OrdSide ordSide;
	protected OrdType ordType;
	protected OrdStatus ordStatus;
	protected OrdTimestamps ordTimestamps;
	protected int strategyId;
	protected int subAccountId;

	private MutableLongObjectMap<ParentOrder> actualOrders = ECollections.newLongObjectHashMap();

}
