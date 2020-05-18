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
		if (ordSysId == 0)
			throw new OrderRefNotFoundException(orderRef);
		return ordSysId;
	}

	public static String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
		String orderRef = Singleton.SysIdMappingRef.get(ordSysId);
		if (orderRef == null)
			throw new OrderRefNotFoundException(ordSysId);
		return orderRef;
	}

}
