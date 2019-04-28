package io.ffreedom.redstone.core.position;

@FunctionalInterface
public interface PositionFactory<T extends Position> {

	default T produce(int accountId, int instrumentId) {
		return produce(accountId, instrumentId, 0);
	}

	T produce(int accountId, int instrumentId, double qty);

}
