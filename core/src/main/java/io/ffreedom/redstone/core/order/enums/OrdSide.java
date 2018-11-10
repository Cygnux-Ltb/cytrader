package io.ffreedom.redstone.core.order.enums;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;

public enum OrdSide {

	Invalid(-1, false, false),

	BUY(1, true, false),

	SELL(2, false, true),

	MARGIN_BUY(3, true, false),

	SHORT_SELL(4, false, true),

	;

	private int code;
	private boolean isBuy;
	private boolean isSell;

	private static Logger logger = LoggerFactory.getLogger(OrdStatus.class);

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

	public static OrdSide valueOf(int code) {
		switch (code) {
		case 1:return BUY;
		case 2:return SELL;
		case 3:return MARGIN_BUY;
		case 4:return SHORT_SELL;
		default:
			logger.error("OrdSide.valueOf(code=={}) -> is no matches, return OrdSide.Invalid", code);
			return Invalid;
		}
	}

}
