package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdSort;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;
import io.ffreedom.redstone.core.order.structure.StopLoss;
import io.ffreedom.redstone.core.order.utils.OrdSysIdGenerate;

/**
 * 用于记录策略在出现交易信号后需要进行的交易
 * 
 * @author yellow013
 */
public final class VirtualOrder extends AbstractOrder {

	private MutableLongObjectMap<ParentOrder> actualOrders = ECollections.newLongObjectHashMap();

	protected VirtualOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			OrdStatus ordStatus, OrdTimestamps ordTimestamps, int strategyId, int subAccountId, StopLoss stopLoss) {
		super(OrdSysIdGenerate.next(strategyId), instrument, ordQtyPrice, ordSide, ordType, ordStatus, ordTimestamps,
				strategyId, subAccountId, stopLoss);
	}

	public MutableLongObjectMap<ParentOrder> getActualOrders() {
		return actualOrders;
	}

	public void addActualOrder(ParentOrder parentOrder) {
		actualOrders.put(parentOrder.getOrdSysId(), parentOrder);
	}

	@Override
	public OrdSort getSort() {
		return OrdSort.Virtual;
	}

}
