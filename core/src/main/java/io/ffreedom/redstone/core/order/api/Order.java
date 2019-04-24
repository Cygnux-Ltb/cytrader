package io.ffreedom.redstone.core.order.api;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdSort;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;
import io.ffreedom.redstone.core.order.structure.StopLoss;
import io.ffreedom.redstone.core.order.structure.TradeSet;

public interface Order extends Comparable<Order> {

	public interface Constant {

		double OrdMinPrice = 0.0001D;

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
	long getOrdSysId();

	Instrument getInstrument();

	OrdQtyPrice getQtyPrice();

	OrdSide getSide();

	OrdType getType();

	OrdStatus getStatus();

	OrdTimestamps getTimestamps();

	void setStatus(OrdStatus ordStatus);

	int getStrategyId();

	int getSubAccountId();

	OrdSort getSort();

	// void setStopLoss(StopLoss stopLoss);

	StopLoss getStopLoss();

	/**
	 * Order成交列表
	 * 
	 * @return TradeSet
	 */
	TradeSet getTradeSet();

	@Override
	default int compareTo(Order o) {
		return getOrdSysId() < o.getOrdSysId() ? -1 : getOrdSysId() > o.getOrdSysId() ? 1 : 0;
	}

}
