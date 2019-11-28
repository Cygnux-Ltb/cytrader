package io.redstone.core.order.enums;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;

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

	private static Logger logger = CommonLoggerFactory.getLogger(OrdStatus.class);

	/**
	 * @param code
	 */
	private OrdStatus(int code, boolean isActive) {
		this.code = code;
		this.isActive = isActive;
	}

	public int code() {
		return code;
	}

	public boolean isActive() {
		return isActive;
	}

	public static OrdStatus valueOf(int code) {
		switch (code) {
		case 10:
			return PendingNew;
		case 11:
			return New;
		case 12:
			return NewRejected;
		case 20:
			return PendingCancel;
		case 21:
			return Canceled;
		case 22:
			return CancelRejected;
		case 30:
			return PendingReplace;
		case 31:
			return Replaced;
		case 41:
			return Suspended;
		case 51:
			return PartiallyFilled;
		case 52:
			return Filled;
		default:
			logger.error("OrdStatus.valueOf(code=={}) -> is no matches, return OrdStatus.Invalid", code);
			return Invalid;
		}

	}

}
