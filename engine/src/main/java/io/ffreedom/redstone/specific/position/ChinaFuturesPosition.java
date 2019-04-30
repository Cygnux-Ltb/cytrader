package io.ffreedom.redstone.specific.position;

import org.eclipse.collections.api.map.primitive.MutableLongLongMap;

import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.position.impl.GenericT0Position;

public final class ChinaFuturesPosition extends GenericT0Position {

	private long beforeTodayQty;
	private MutableLongLongMap beforeTodayQtyLockRecord;

	public final static ChinaFuturesPosition EMPTY = new ChinaFuturesPosition(-1, -1);

	private ChinaFuturesPosition(int accountId, int instrumentId) {
		this(accountId, instrumentId, 0);
	}

	private ChinaFuturesPosition(int accountId, int instrumentId, long beforeTodayQty) {
		super(accountId, instrumentId);
		this.beforeTodayQty = beforeTodayQty;
	}

	final static ChinaFuturesPosition newInstance(int accountId, int instrumentId) {
		return new ChinaFuturesPosition(accountId, instrumentId);
	}

	final static ChinaFuturesPosition newInstance(int accountId, int instrumentId, long beforeTodayQty) {
		return new ChinaFuturesPosition(accountId, instrumentId, beforeTodayQty);
	}

	public double getBeforeTodayQty() {
		return beforeTodayQty;
	}

	public double lockBeforeTodayQty(long ordSysId, OrdSide side, long lockQty) {
		if (beforeTodayQty == 0) {
			return 0;
		} else if (beforeTodayQty > 0) {
			switch (side.direction()) {
			case Short:
				// 需要锁定的Qty小于或等于昨仓,实际锁定量等于请求量
				if (lockQty <= beforeTodayQty) {
					// 记录此订单锁定量
					beforeTodayQtyLockRecord.put(ordSysId, lockQty);
					beforeTodayQty -= lockQty;
					return lockQty;
				}
				// 需要锁定的Qty大于昨仓,实际锁定量等于全部剩余量
				else {
					beforeTodayQtyLockRecord.put(ordSysId, beforeTodayQty);
					beforeTodayQty = 0;
					return beforeTodayQty;
				}
			default:
				return 0;
			}
		} else {
			switch (side.direction()) {
			case Long:
				// 需要锁定的Qty小于或等于昨仓,实际锁定量等于请求量
				long absBeforeTodayQty = Math.abs(beforeTodayQty);
				if (lockQty <= absBeforeTodayQty) {
					// 记录此订单锁定量
					beforeTodayQtyLockRecord.put(ordSysId, lockQty);
					beforeTodayQty += lockQty;
					return lockQty;
				} else {
					beforeTodayQtyLockRecord.put(ordSysId, absBeforeTodayQty);
					beforeTodayQty = 0;
					return absBeforeTodayQty;
				}
			default:
				return 0;
			}
		}
	}

	// TODO
	public double unlockBeforeTodayQty(long ordSysId, OrdSide side, double unlockQty) {
		return 0;
	}

	@Override
	public void updatePosition(Order order) {
		OrdStatus status = order.getStatus();
		OrdQtyPrice ordQtyPrice = order.getQtyPrice();
		switch (status) {
		case PartiallyFilled:
		case Filled:
			switch (order.getSide().direction()) {
			case Long:
				setCurrentQty(getCurrentQty() + ordQtyPrice.getFilledQty() - ordQtyPrice.getLastFilledQty());
				break;
			case Short:
				setCurrentQty(getCurrentQty() - ordQtyPrice.getFilledQty() + ordQtyPrice.getLastFilledQty());
			default:
				break;
			}
			break;
		default:
			break;
		}

	}

}
