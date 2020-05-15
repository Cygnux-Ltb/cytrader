package io.redstone.core.order.enums;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;

public enum OrdStatus {

	Invalid(-1, true),

	PendingNew(1, false),

	New(2, false),

	PendingCancel(4, false),

	PartiallyFilled(8, false),

	Filled(16, true),

	Canceled(32, true),

	NewRejected(64, true),

	CancelRejected(128, true),

	@Deprecated
	PendingReplace(101, false),

	@Deprecated
	Replaced(102, true),

	@Deprecated
	Suspended(103, true),

	;

	private int code;
	private boolean isTerminated;

	private static final Logger log = CommonLoggerFactory.getLogger(OrdStatus.class);

	/**
	 * @param code
	 */
	private OrdStatus(int code, boolean isTerminated) {
		this.code = code;
		this.isTerminated = isTerminated;
	}

	public int code() {
		return code;
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public static OrdStatus valueOf(int code) {
		switch (code) {
		case 1:
			return PendingNew;
		case 2:
			return New;
		case 4:
			return PendingCancel;
		case 8:
			return PartiallyFilled;
		case 16:
			return Filled;
		case 32:
			return Canceled;
		case 64:
			return NewRejected;
		case 128:
			return CancelRejected;
		case 101:
			return PendingReplace;
		case 102:
			return Replaced;
		case 103:
			return Suspended;
		default:
			log.error("OrdStatus.valueOf(code=={}) -> is no matches, return OrdStatus.Invalid", code);
			return Invalid;
		}

	}

}
