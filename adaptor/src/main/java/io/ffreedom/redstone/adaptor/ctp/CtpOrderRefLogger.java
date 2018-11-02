package io.ffreedom.redstone.adaptor.ctp;

import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.impl.factory.BiMaps;

/**
 * 
 * @author phoneix
 *
 *         TODO - Persistence
 */

class CtpOrderRefLogger {

	private MutableBiMap<Integer, Long> ctpOrderBook = BiMaps.mutable.empty();

	final static CtpOrderRefLogger INSTANCE = new CtpOrderRefLogger();

	private CtpOrderRefLogger() {
	}

	void put(Integer orderRef, Long orderSysId) {
		ctpOrderBook.put(orderRef, orderSysId);
	}

	Long getOrderSysId(Integer orderRef) throws CtpOrderRefNotFoundException {
		if (ctpOrderBook.containsKey(orderRef)) {
			return ctpOrderBook.get(orderRef);
		}
		throw new CtpOrderRefNotFoundException(orderRef);
	}

	Integer getOrderRef(Long orderSysId) throws CtpOrderRefNotFoundException {
		if (ctpOrderBook.inverse().containsKey(orderSysId)) {
			return ctpOrderBook.inverse().get(orderSysId);
		}
		throw new CtpOrderRefNotFoundException(orderSysId);
	}

}
