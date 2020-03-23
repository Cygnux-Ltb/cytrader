package io.mercury.indicator.impl.ma.base;

public final class MaCrossPredicate {

	public static boolean isUpCross(double shortMa, double longMa) {
		if (!Double.isNaN(shortMa) && !Double.isNaN(longMa) && shortMa > longMa)
			return true;
		else
			return false;
	}

	public static boolean isDownCross(double shortMa, double longMa) {
		if (!Double.isNaN(shortMa) && !Double.isNaN(longMa) && shortMa < longMa)
			return true;
		else
			return false;
	}

}
