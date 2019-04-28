package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdSort;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;

/**
 * 用于记录策略在出现交易信号后需要进行的交易
 * 
 * @author yellow013
 */
public final class VirtualOrder extends AbstractOrder {

	private MutableLongObjectMap<ParentOrder> actualOrders = MutableMaps.newLongObjectHashMap();

	private VirtualOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		super(instrument, ordQtyPrice, ordSide, ordType, strategyId, subAccountId);
	}

	public static VirtualOrder newVirtualOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId) {
		return new VirtualOrder(instrument, ordQtyPrice, ordSide, ordType, strategyId, subAccountId);
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
