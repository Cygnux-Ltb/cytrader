package io.redstone.core.order.api;

import io.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdSort;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdQtyPrice;
import io.redstone.core.order.structure.OrdTimestamps;
import io.redstone.core.order.structure.StopLoss;

public interface Order extends Comparable<Order> {

	public interface Constant {

		double OrdMinPrice = 0.0000001D;

		double OrdMaxPrice = 1_000_000D;

	}

	/**
	 * ordSysId构成<br>
	 * 策略Id | 时间戳Second | 自增量Number<br>
	 * strategyId | epochSecond| increment<br>
	 * 922 | 3372036854 | 775807<br>
	 * 
	 * @return long
	 */
	long ordSysId();

	Instrument instrument();

	OrdQtyPrice qtyPrice();

	OrdSide side();

	OrdType type();

	OrdStatus status();

	OrdTimestamps timestamps();

	OrdStatus status(OrdStatus ordStatus);

	int strategyId();

	int subAccountId();

	OrdSort sort();

	StopLoss stopLoss();

	@Override
	default int compareTo(Order o) {
		return ordSysId() < o.ordSysId() ? -1 : ordSysId() > o.ordSysId() ? 1 : 0;
	}

}
