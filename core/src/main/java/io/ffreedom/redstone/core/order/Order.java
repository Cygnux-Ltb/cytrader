package io.ffreedom.redstone.core.order;

import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdRank;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

public interface Order extends Comparable<Order> {

	/**
	 * 
	 * ownerId | epochSecond | increment <br>
	 *     922 | 3372036854  | 775807 <br>
	 * 
	 * @return int
	 */

	long getOrdSysId();

	Instrument getInstrument();

	OrdQtyPrice getOrdQtyPrice();

	OrdSide getOrdSide();

	OrdType getOrdType();

	OrdStatus getOrdStatus();

	OrdTimestamps getOrdTimestamps();

	Order setOrdStatus(OrdStatus ordStatus);

	int getStrategyId();

	int getSubAccountId();

	OrdRank getOrdRank();

	TradeList getTradeList();

	@Override
	default int compareTo(Order o) {
		return getOrdSysId() < o.getOrdSysId() ? -1 : getOrdSysId() > o.getOrdSysId() ? 1 : 0;
	}

}
