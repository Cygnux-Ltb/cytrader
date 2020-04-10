package io.mercury.ftdc.adaptor.utils;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;

/**
 * 
 * @author yellow013
 *
 * @TODO - Add Persistence
 */

public class OrderRefKeeper {

	private MutableObjectLongMap<String> orderRefToOrdSysId = MutableMaps.newObjectLongHashMap(Capacity.L10_SIZE_1024);

	private MutableLongObjectMap<String> ordSysIdToOrderRef = MutableMaps.newLongObjectHashMap(Capacity.L10_SIZE_1024);

	private final static OrderRefKeeper Singleton = new OrderRefKeeper();

	private OrderRefKeeper() {
	}

	public static void put(String orderRef, long ordSysId) {
		Singleton.orderRefToOrdSysId.put(orderRef, ordSysId);
		Singleton.ordSysIdToOrderRef.put(ordSysId, orderRef);
	}

	public static long getOrdSysId(String orderRef) throws OrderRefNotFoundException {
		long ordSysId = Singleton.orderRefToOrdSysId.get(orderRef);
		if (ordSysId == 0)
			throw new OrderRefNotFoundException(orderRef);
		return ordSysId;
	}

	public static String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
		String orderRef = Singleton.ordSysIdToOrderRef.get(ordSysId);
		if (orderRef == null)
			throw new OrderRefNotFoundException(ordSysId);
		return orderRef;
	}

}
