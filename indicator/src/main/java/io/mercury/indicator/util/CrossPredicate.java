package io.mercury.indicator.util;

import org.eclipse.collections.api.block.predicate.primitive.LongLongPredicate;

public final class CrossPredicate {

	public static final LongLongPredicate UpCross = (s, l) -> s > l;

	public static final LongLongPredicate DownCross = (s, l) -> s < l;

}
