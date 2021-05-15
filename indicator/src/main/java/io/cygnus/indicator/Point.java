package io.cygnus.indicator;

import io.mercury.common.sequence.Serial;

public interface Point<S extends Serial> extends Comparable<Point<S>> {

	int getIndex();

	S getSerial();

	@Override
	default int compareTo(Point<S> o) {
		return getSerial().compareTo(o.getSerial());
	}

}
