package io.mercury.ftdc.adaptor;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.slf4j.Logger;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;
import io.mercury.redstone.core.order.OrdSysIdSupporter;

/**
 * 
 * @author yellow013
 *
 * @TODO - Add Persistence
 */

public class OrderRefKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(OrderRefKeeper.class);

	private final MutableObjectLongMap<String> MappingOrdSysId = MutableMaps
			.newObjectLongHashMap(Capacity.L10_SIZE_1024);

	private final MutableLongObjectMap<String> MappingOrderRef = MutableMaps
			.newLongObjectHashMap(Capacity.L10_SIZE_1024);

	private final static OrderRefKeeper StaticInstance = new OrderRefKeeper();

	private OrderRefKeeper() {
	}

	public static void put(String orderRef, long ordSysId) {
		log.info("orderRef==[{}] Mapping to ordSysId==[{}]", orderRef, ordSysId);
		StaticInstance.MappingOrdSysId.put(orderRef, ordSysId);
		StaticInstance.MappingOrderRef.put(ordSysId, orderRef);
	}

	public static long getOrdSysId(String orderRef) {
		long ordSysId = StaticInstance.MappingOrdSysId.get(orderRef);
		if (ordSysId == 0L) {
			// 处理其他来源的订单
			ordSysId = OrdSysIdSupporter.allocateIdForExternal();
			log.warn("Handle third order, allocate third ordSysId==[{}], orderRef==[{}]", ordSysId, orderRef);
		}
		return ordSysId;
	}

	public static String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
		String orderRef = StaticInstance.MappingOrderRef.get(ordSysId);
		if (orderRef == null)
			throw new OrderRefNotFoundException(ordSysId);
		return orderRef;
	}

}
