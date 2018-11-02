package io.ffreedom.redstone.core.strategy;

import io.ffreedom.financial.Instrument;

public interface CircuitBreaker {

	void openStrategy();

	void closeStrategy();

	void openInstrument(Instrument instrument);

	void closeInstrument(Instrument instrument);

}
