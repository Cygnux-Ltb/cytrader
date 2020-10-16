package io.mercury.indicator.api;

import io.mercury.common.sequence.Serial;

public interface Point<S extends Serial> extends Comparable<Point<S>> {

	int index();

	S serial();

	@Override
	default int compareTo(Point<S> o) {
		return serial().compareTo(o.serial());
	}

}
