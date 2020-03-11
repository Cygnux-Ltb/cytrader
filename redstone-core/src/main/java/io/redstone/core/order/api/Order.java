package io.redstone.core.order.api;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdLevel;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.OrdTimestamps;
import io.redstone.core.order.structure.StopLoss;

public interface Order extends Comparable<Order> {

	public interface Const {

		long OrdMinPrice = 1L;

		long OrdMaxPrice = 100000000;

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

	OrdQty ordQty();
	
	OrdPrice ordPrice();

	OrdSide ordSide();

	OrdType ordType();

	OrdStatus ordStatus();

	OrdTimestamps ordTimestamps();

	OrdStatus ordStatus(OrdStatus ordStatus);

	int strategyId();

	int subAccountId();

	OrdLevel ordLevel();

	StopLoss stopLoss();

	@Override
	default int compareTo(Order o) {
		return ordSysId() < o.ordSysId() ? -1 : ordSysId() > o.ordSysId() ? 1 : 0;
	}

}
