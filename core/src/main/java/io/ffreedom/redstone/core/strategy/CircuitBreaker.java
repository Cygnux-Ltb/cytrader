package io.ffreedom.redstone.core.strategy;

public interface CircuitBreaker {

	void enableStrategy();

	void disableStrategy();

	void enableAccount(int accountId);

	void disableAccount(int accountId);

	void enableInstrument(int instrumentId);

	void disableInstrument(int instrumentId);

}
