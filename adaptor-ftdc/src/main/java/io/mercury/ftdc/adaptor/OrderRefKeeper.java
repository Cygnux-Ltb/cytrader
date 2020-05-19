package io.mercury.ftdc.adaptor;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.slf4j.Logger;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;
import io.mercury.redstone.core.order.OrdSysIdAllocator;

/**
 * 
 * @author yellow013
 *
 * @TODO - Add Persistence
 */

public class OrderRefKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(OrderRefKeeper.class);

	private final MutableObjectLongMap<String> RefMappingSysId = MutableMaps
			.newObjectLongHashMap(Capacity.L10_SIZE_1024);

	private final MutableLongObjectMap<String> SysIdMappingRef = MutableMaps
			.newLongObjectHashMap(Capacity.L10_SIZE_1024);

	private final static OrderRefKeeper Singleton = new OrderRefKeeper();

	private OrderRefKeeper() {
	}

	public static void put(String orderRef, long ordSysId) {
		Singleton.RefMappingSysId.put(orderRef, ordSysId);
		Singleton.SysIdMappingRef.put(ordSysId, orderRef);
	}

	public static long getOrdSysId(String orderRef) throws OrderRefNotFoundException {
		long ordSysId = Singleton.RefMappingSysId.get(orderRef);
		if (ordSysId == 0L) {
			// 处理其他来源的订单
			ordSysId = OrdSysIdAllocator.allocateFromThird();
			log.warn("Handle third order, allocate third ordSysId==[{}], orderRef==[{}]", ordSysId, orderRef);
		}
		return ordSysId;
	}

	public static String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
		String orderRef = Singleton.SysIdMappingRef.get(ordSysId);
		if (orderRef == null)
			throw new OrderRefNotFoundException(ordSysId);
		return orderRef;
	}

}
