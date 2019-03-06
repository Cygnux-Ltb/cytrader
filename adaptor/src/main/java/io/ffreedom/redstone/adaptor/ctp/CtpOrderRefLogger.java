package io.ffreedom.redstone.adaptor.ctp;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;

import io.ffreedom.common.collect.ECollections;

/**
 * 
 * @author phoneix
 *
 *         TODO - Persistence
 */

public class CtpOrderRefLogger {

	private MutableObjectLongMap<String> orderRefMappingToOrdSysId = ECollections.newObjectLongHashMap(1024);

	private MutableLongObjectMap<String> ordSysIdMappingToOrderRef = ECollections.newLongObjectHashMap(1024);

	private final static CtpOrderRefLogger INSTANCE = new CtpOrderRefLogger();

	private CtpOrderRefLogger() {
	}

	static void put(String orderRef, long ordSysId) {
		INSTANCE.orderRefMappingToOrdSysId.put(orderRef, ordSysId);
		INSTANCE.ordSysIdMappingToOrderRef.put(ordSysId, orderRef);
	}

	static long getOrdSysId(String orderRef) throws CtpOrderRefNotFoundException {
		if (INSTANCE.orderRefMappingToOrdSysId.containsKey(orderRef))
			return INSTANCE.orderRefMappingToOrdSysId.get(orderRef);
		throw new CtpOrderRefNotFoundException(orderRef);
	}

	static String getOrderRef(long ordSysId) throws CtpOrderRefNotFoundException {
		if (INSTANCE.ordSysIdMappingToOrderRef.containsKey(ordSysId)) {
			return INSTANCE.ordSysIdMappingToOrderRef.get(ordSysId);
		}
		throw new CtpOrderRefNotFoundException(ordSysId);
	}

}
