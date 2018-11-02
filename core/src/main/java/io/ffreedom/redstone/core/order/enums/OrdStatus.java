package io.ffreedom.redstone.core.order.enums;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntObjectMapFactoryImpl;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;

public enum OrdStatus {

	Invalid(-1, false),

	PendingNew(10, true),

	New(11, true),

	NewRejected(12, false),

	PendingCancel(20, true),

	Canceled(21, false),

	CancelRejected(22, false),

	PendingReplace(30, true),

	Replaced(31, true),

	Suspended(41, true),

	PartiallyFilled(51, true),

	Filled(52, false),

	;

	private int code;
	private boolean isActive;

	private static Logger logger = LoggerFactory.getLogger(OrdStatus.class);

	/**
	 * @param code
	 */
	private OrdStatus(int code, boolean isActive) {
		this.code = code;
		this.isActive = isActive;
	}

	public int getCode() {
		return code;
	}

	public boolean isActive() {
		return isActive;
	}

	private static ImmutableIntObjectMap<OrdStatus> codeMap;

	static {
		synchronized (OrdStatus.class) {
			if (codeMap == null) {
				MutableIntObjectMap<OrdStatus> tempMap = IntObjectHashMap.newMap();
				for (OrdStatus ordStatus : OrdStatus.values()) {
					tempMap.put(ordStatus.getCode(), ordStatus);
				}
				codeMap = ImmutableIntObjectMapFactoryImpl.INSTANCE.withAll(tempMap);
			}
		}
	}

	public static OrdStatus valueOf(int code) {
		OrdStatus ordStatus = codeMap.get(code);
		if (ordStatus == null) {
			logger.error("OrdStatus.valueOf(code=={}) -> is not find, return OrdStatus.Invalid");
			return Invalid;
		}
		return ordStatus;
	}

}
