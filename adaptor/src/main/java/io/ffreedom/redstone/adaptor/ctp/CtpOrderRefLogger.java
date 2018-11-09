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

	void put(Integer orderRef, Long ordSysId) {
		ctpOrderBook.put(orderRef, ordSysId);
	}

	Long getOrdSysId(Integer orderRef) throws CtpOrderRefNotFoundException {
		if (ctpOrderBook.containsKey(orderRef)) {
			return ctpOrderBook.get(orderRef);
		}
		throw new CtpOrderRefNotFoundException(orderRef);
	}

	Integer getOrderRef(Long ordSysId) throws CtpOrderRefNotFoundException {
		if (ctpOrderBook.inverse().containsKey(ordSysId)) {
			return ctpOrderBook.inverse().get(ordSysId);
		}
		throw new CtpOrderRefNotFoundException(ordSysId);
	}

}
