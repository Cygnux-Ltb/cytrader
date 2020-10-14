package io.mercury.ftdc.adaptor;

import static io.mercury.common.collections.MutableMaps.newLongObjectHashMap;
import static io.mercury.common.collections.MutableMaps.newObjectLongHashMap;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.slf4j.Logger;

import io.mercury.common.collections.Capacity;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;
import io.mercury.redstone.core.order.OrderUniqueIds;

/**
 * 
 * @author yellow013
 *
 * @TODO - Add Persistence
 */

public class OrderRefKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(OrderRefKeeper.class);

	private final MutableObjectLongMap<String> mapOfUniqueId = newObjectLongHashMap(Capacity.L10_SIZE_1024);

	private final MutableLongObjectMap<String> mapOfOrderRef = newLongObjectHashMap(Capacity.L10_SIZE_1024);

	private final static OrderRefKeeper StaticInstance = new OrderRefKeeper();

	private OrderRefKeeper() {
	}

	public static void put(String orderRef, long uniqueId) {
		log.info("orderRef==[{}] Mapping to uniqueId==[{}]", orderRef, uniqueId);
		StaticInstance.mapOfUniqueId.put(orderRef, uniqueId);
		StaticInstance.mapOfOrderRef.put(uniqueId, orderRef);
	}

	public static long getUniqueId(String orderRef) {
		long uniqueId = StaticInstance.mapOfUniqueId.get(orderRef);
		if (uniqueId == 0L) {
			// 处理其他来源的订单
			uniqueId = OrderUniqueIds.allocateIdForExternal();
			log.warn("Handle third order, allocate third uniqueId==[{}], orderRef==[{}]", uniqueId, orderRef);
		}
		return uniqueId;
	}

	public static String getOrderRef(long uniqueId) throws OrderRefNotFoundException {
		String orderRef = StaticInstance.mapOfOrderRef.get(uniqueId);
		if (orderRef == null)
			throw new OrderRefNotFoundException(uniqueId);
		return orderRef;
	}

}
