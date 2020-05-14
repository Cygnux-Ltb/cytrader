package io.redstone.core.order;

import org.slf4j.Logger;

import io.mercury.common.thread.ThreadHelper;
import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.OrdTimestamps;

public interface Order extends Comparable<Order> {

	/**
	 * ordSysId构成<br>
	 * 策略Id | 时间戳Second | 自增量Number<br>
	 * strategyId | epochSecond| increment<br>
	 * 922 | 3372036854 | 775807<br>
	 * 
	 * @return long
	 */
	long ordSysId();

	int strategyId();

	int subAccountId();

	Instrument instrument();

	OrdQty ordQty();

	OrdPrice ordPrice();

	OrdType ordType();

	TrdDirection direction();

	OrdTimestamps ordTimestamps();

	OrdStatus ordStatus();

	void setOrdStatus(OrdStatus ordStatus);

	String remark();

	void setRemark(String remark);

	int ordLevel();

	long ownerOrdId();

	void outputInfoLog(Logger log, String objName, String msg);

	@Override
	default int compareTo(Order o) {
		return ordLevel() < o.ordLevel() ? -1
				: ordLevel() < o.ordLevel() ? 1 : ordSysId() < o.ordSysId() ? -1 : ordSysId() > o.ordSysId() ? 1 : 0;
	}

	public static void main(String[] args) {

		System.out.println(Long.MAX_VALUE);

		long seed = System.nanoTime();
		long seed1 = System.currentTimeMillis();
		System.out.println(seed);
		System.out.println(seed1);
		for (;;) {
			System.out.println((System.nanoTime() - seed) / 100000);
			ThreadHelper.sleep(20);
		}

	}

}
