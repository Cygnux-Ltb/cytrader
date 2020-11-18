package io.cygnus.engine.position;

import org.eclipse.collections.api.map.primitive.MutableLongLongMap;

import io.horizon.definition.order.Order;
import io.horizon.definition.order.enums.OrdStatus;
import io.horizon.definition.order.enums.TrdDirection;
import io.horizon.definition.order.structure.OrdQty;
import io.horizon.definition.position.PositionT0;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;

public final class ChinaFuturesPosition extends PositionT0 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4650337450176161266L;

	private int beforeTodayQty;

	private MutableLongLongMap beforeTodayQtyLockRecord = MutableMaps.newLongLongHashMap(Capacity.L06_SIZE_64);

	ChinaFuturesPosition(int accountId, int instrumentId) {
		this(accountId, instrumentId, 0);
	}

	ChinaFuturesPosition(int accountId, int instrumentId, int beforeTodayQty) {
		super(accountId, instrumentId);
		this.beforeTodayQty = beforeTodayQty;
	}

	public int beforeTodayQty() {
		return beforeTodayQty;
	}

	public int lockBeforeTodayQty(long uniqueId, TrdDirection direction, int lockQty) {
		if (beforeTodayQty == 0) {
			return 0;
		} else if (beforeTodayQty > 0) {
			switch (direction) {
			case Short:
				// 需要锁定的Qty小于或等于昨仓, 实际锁定量等于请求量
				if (lockQty <= beforeTodayQty) {
					// 记录此订单锁定量
					beforeTodayQtyLockRecord.put(uniqueId, lockQty);
					beforeTodayQty -= lockQty;
					return lockQty;
				}
				// 需要锁定的Qty大于昨仓,实际锁定量等于全部剩余量
				else {
					beforeTodayQtyLockRecord.put(uniqueId, beforeTodayQty);
					beforeTodayQty = 0;
					return beforeTodayQty;
				}
			default:
				return 0;
			}
		} else {
			switch (direction) {
			case Long:
				// 需要锁定的Qty小于或等于昨仓, 实际锁定量等于请求量
				int absBeforeTodayQty = Math.abs(beforeTodayQty);
				if (lockQty <= absBeforeTodayQty) {
					// 记录此订单锁定量
					beforeTodayQtyLockRecord.put(uniqueId, lockQty);
					beforeTodayQty += lockQty;
					return lockQty;
				} else {
					beforeTodayQtyLockRecord.put(uniqueId, absBeforeTodayQty);
					beforeTodayQty = 0;
					return absBeforeTodayQty;
				}
			default:
				return 0;
			}
		}
	}

	// TODO 解锁可用仓位
	public int unlockBeforeTodayQty(long uniqueId, TrdDirection direction, int unlockQty) {
		return 0;
	}

	@Override
	public void updatePosition(Order order) {
		OrdStatus status = order.status();
		OrdQty qty = order.qty();
		switch (status) {
		case PartiallyFilled:
		case Filled:
			switch (order.direction()) {
			case Long:
				setCurrentQty(currentQty() + qty.filledQty() - qty.lastFilledQty());
				break;
			case Short:
				setCurrentQty(currentQty() - qty.filledQty() + qty.lastFilledQty());
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}

	}

}
