package io.mercury.financial.instrument;

import io.mercury.common.fsm.Enable;

public interface Instrument extends Enable<Instrument> {

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

	boolean isAvailableImmediately();

	default PriorityClose priorityClose() {
		return PriorityClose.NONE;
	}

	default PriceMultiplier priceMultiplier() {
		return symbol().priceMultiplier();
	}

	public static enum PriorityClose {
		NONE, YESTERDAY, TODAY
	}

	String fmtText();

}
