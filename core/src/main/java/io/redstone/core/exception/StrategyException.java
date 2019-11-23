package io.redstone.core.exception;

public class StrategyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1875202743194241352L;

	public StrategyException(int strategyId, String message) {
		super(message);
	}

	public StrategyException(int strategyId, Throwable throwable) {
		super(throwable);
	}

}
