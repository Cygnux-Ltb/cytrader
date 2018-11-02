package io.ffreedom.redstone.core.order.enums;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntObjectMapFactoryImpl;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;

public enum OrdSide {

	Invalid(-1, false, false),

	BUY(0, true, false),

	SELL(1, true, false),

	MARGIN_BUY(2, false, true),

	SHORT_SELL(3, false, true),

	;

	private int code;
	private boolean isBuy;
	private boolean isSell;

	private static Logger logger = LoggerFactory.getLogger(OrdSide.class);

	private OrdSide(int code, boolean isBuy, boolean isSell) {
		this.code = code;
		this.isBuy = isBuy;
		this.isSell = isSell;
	}

	public int getCode() {
		return code;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public boolean isSell() {
		return isSell;
	}

	private static ImmutableIntObjectMap<OrdSide> codeMap;

	static {
		synchronized (OrdSide.class) {
			if (codeMap == null) {
				MutableIntObjectMap<OrdSide> tempMap = IntObjectHashMap.newMap();
				for (OrdSide ordSide : OrdSide.values()) {
					tempMap.put(ordSide.getCode(), ordSide);
				}
				codeMap = ImmutableIntObjectMapFactoryImpl.INSTANCE.withAll(tempMap);
			}
		}
	}

	public static OrdSide valueOf(int code) {
		OrdSide ordSide = codeMap.get(code);
		if (ordSide == null) {
			logger.error("OrdSide.valueOf(code=={}) -> is not find, return OrdSide.Invalid");
			return Invalid;
		}
		return ordSide;
	}

}
