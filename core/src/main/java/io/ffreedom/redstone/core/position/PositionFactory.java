package io.ffreedom.redstone.core.position;

import io.ffreedom.common.functional.Factory;
import io.ffreedom.financial.Instrument;

@FunctionalInterface
public interface PositionFactory<T extends Position> extends Factory<Instrument, T> {

	T produce(Instrument instrument);

}
