package io.mercury.ftdc.adaptor;

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

	private MutableObjectLongMap<String> refMappingSysId = MutableMaps.newObjectLongHashMap(Capacity.L10_SIZE_1024);

	private MutableLongObjectMap<String> sysIdMappingRef = MutableMaps.newLongObjectHashMap(Capacity.L10_SIZE_1024);

	private final static OrderRefKeeper Singleton = new OrderRefKeeper();

	private OrderRefKeeper() {
	}

	public static void put(String orderRef, long ordSysId) {
		Singleton.refMappingSysId.put(orderRef, ordSysId);
		Singleton.sysIdMappingRef.put(ordSysId, orderRef);
	}

	public static long getOrdSysId(String orderRef) throws OrderRefNotFoundException {
		long ordSysId = Singleton.refMappingSysId.get(orderRef);
		if (ordSysId == 0)
			throw new OrderRefNotFoundException(orderRef);
		return ordSysId;
	}

	public static String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
		String orderRef = Singleton.sysIdMappingRef.get(ordSysId);
		if (orderRef == null)
			throw new OrderRefNotFoundException(ordSysId);
		return orderRef;
	}

}
