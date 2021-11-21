package io.cygnus.indicator.util;

import org.eclipse.collections.api.block.predicate.primitive.DoubleDoublePredicate;
import org.eclipse.collections.api.block.predicate.primitive.LongLongPredicate;

public final class CrossPredicate {

	public static final LongLongPredicate L_UpCross = (s, l) -> s > l;

	public static final LongLongPredicate L_DownCross = (s, l) -> s < l;
	
	public static final DoubleDoublePredicate D_UpCross = (s, l) -> s > l;

	public static final DoubleDoublePredicate D_DownCross = (s, l) -> s < l;

}
