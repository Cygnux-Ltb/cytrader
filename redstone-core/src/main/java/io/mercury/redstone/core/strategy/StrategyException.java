package io.mercury.redstone.core.strategy;

public class StrategyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8145540141394714301L;

	public StrategyException(int strategyId, String message) {
		super("strategy id -> [" + strategyId + "] throw StrategyException, message -> [" + message + "]");
	}

	public StrategyException(int strategyId, Throwable throwable) {
		super("strategy id -> [" + strategyId + "] throw StrategyException", throwable);
	}

}
