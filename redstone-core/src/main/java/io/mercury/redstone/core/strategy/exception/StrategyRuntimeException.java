package io.mercury.redstone.core.strategy;

public class StrategyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1875202743194241352L;

	public StrategyException(int strategyId, String message) {
		super("strategy id -> [" + strategyId + "] throw StrategyException, message -> [" + message + "]");
	}

	public StrategyException(int strategyId, Throwable throwable) {
		super("strategy id -> [" + strategyId + "] throw StrategyException", throwable);
	}

}
