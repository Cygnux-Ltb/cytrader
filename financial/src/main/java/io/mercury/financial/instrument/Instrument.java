package io.mercury.financial.instrument;

import io.mercury.common.fsm.Enable;

public interface Instrument extends Enable {

	/**
	 * STOCK : exchange|symbol<br>
	 * MAX_VALUE == 214|7483647<br>
	 * 
	 * FUTURES:exchange|symbol|term<br>
	 * MAX_VALUE == 214| 74 |83647<br>
	 * 
	 * @return int
	 */
	int id();

	InstrumentType type();

	String code();

	Symbol symbol();

	boolean isAvailableNow();

	default PriorityCloseType priorityCloseType() {
		return PriorityCloseType.NONE;
	}

	default PriceMultiplier priceMultiplier() {
		return symbol().priceMultiplier();
	}

	public static enum InstrumentType {

		BOND,

		OPTION,

		STOCK,

		FUTURES,

		FOREX,

		;

	}

	public static enum PriorityCloseType {
		NONE, BEFORE_TODAY
	}

	String fmtText();

}
