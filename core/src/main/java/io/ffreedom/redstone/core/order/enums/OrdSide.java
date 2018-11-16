package io.ffreedom.redstone.core.order.enums;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.redstone.core.trade.enums.TrdDirection;

public enum OrdSide {

	Invalid(-1, TrdDirection.Invalid),

	Buy(1, TrdDirection.Long),

	Sell(2, TrdDirection.Short),

	MarginBuy(3, TrdDirection.Long),

	ShortSell(4, TrdDirection.Short),

	;

	private int code;
	private TrdDirection direction;

	private static Logger logger = LoggerFactory.getLogger(OrdStatus.class);

	private OrdSide(int code, TrdDirection direction) {
		this.code = code;
		this.direction = direction;
	}

	public int getCode() {
		return code;
	}

	public TrdDirection getDirection() {
		return direction;
	}

	public static OrdSide valueOf(int code) {
		switch (code) {
		case 1:return Buy;
		case 2:return Sell;
		case 3:return MarginBuy;
		case 4:return ShortSell;
		default:
			logger.error("OrdSide.valueOf(code=={}) -> is no matches, return OrdSide.Invalid", code);
			return Invalid;
		}
	}

}
