package io.redstone.core.order.impl;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdSort;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;

/**
 * 用于记录策略在出现交易信号后需要进行的交易
 * 
 * @author yellow013
 */
public final class VirtualOrder extends AbstractOrder {

	private MutableLongObjectMap<ParentOrder> actualOrders = MutableMaps.newLongObjectHashMap();

	private VirtualOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		super(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
	}

	public static VirtualOrder newVirtualOrder(Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, OrdSide ordSide,
			OrdType ordType, int strategyId, int subAccountId) {
		return new VirtualOrder(instrument, ordQty, ordPrice, ordSide, ordType, strategyId, subAccountId);
	}

	public MutableLongObjectMap<ParentOrder> getActualOrders() {
		return actualOrders;
	}

	public void addActualOrder(ParentOrder parentOrder) {
		actualOrders.put(parentOrder.ordSysId(), parentOrder);
	}

	@Override
	public OrdSort sort() {
		return OrdSort.Virtual;
	}

	public long getVirtualId() {
		return ordSysId();
	}

}
