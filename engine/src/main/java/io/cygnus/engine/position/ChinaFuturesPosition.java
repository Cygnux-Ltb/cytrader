package io.cygnus.engine.position;

import org.eclipse.collections.api.map.primitive.MutableLongLongMap;

import io.horizon.transaction.order.OrdEnum.OrdStatus;
import io.horizon.transaction.order.OrdEnum.TrdDirection;
import io.horizon.market.instrument.spec.ChinaFutures;
import io.horizon.transaction.order.OrdQty;
import io.horizon.transaction.order.Order;
import io.horizon.transaction.position.AbstractPosition;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.serialization.json.JsonWrapper;

/**
 * 
 * 针对上期所的平昨仓功能, 设计可以优先平昨仓的仓位管理
 * 
 * @author yellow013
 *
 */
public final class ChinaFuturesPosition extends AbstractPosition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4650337450176161266L;

	// 昨仓
	private int beforeTodayQty;

	// 昨仓锁定记录
	private MutableLongLongMap beforeTodayQtyLockRecord = MutableMaps.newLongLongHashMap(Capacity.L05_SIZE);

	ChinaFuturesPosition(int accountId, ChinaFutures futures) {
		this(accountId, futures, 0);
	}

	ChinaFuturesPosition(int accountId, ChinaFutures futures, int beforeTodayQty) {
		super(accountId, futures);
		this.beforeTodayQty = beforeTodayQty;
	}

	@Override
	public int getTradeableQty() {
		return getCurrentQty();
	}

	@Override
	public void setTradeableQty(int qty) {
		setCurrentQty(qty);
	}

	@Override
	public String toString() {
		return JsonWrapper.toJson(this);
	}

	public int getBeforeTodayQty() {
		return beforeTodayQty;
	}

	public int lockBeforeTodayQty(long ordSysId, TrdDirection direction, int lockQty) {
		if (beforeTodayQty == 0) {
			return 0;
		} else if (beforeTodayQty > 0) {
			switch (direction) {
			case Short:
				// 需要锁定的Qty小于或等于昨仓, 实际锁定量等于请求量
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
			switch (direction) {
			case Long:
				// 需要锁定的Qty小于或等于昨仓, 实际锁定量等于请求量
				int absBeforeTodayQty = Math.abs(beforeTodayQty);
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

	// TODO 解锁可用仓位
	public int unlockBeforeTodayQty(long ordSysId, TrdDirection direction, int unlockQty) {
		return 0;
	}

	@Override
	public void updateWithOrder(Order order) {
		OrdStatus status = order.getStatus();
		OrdQty qty = order.getQty();
		switch (status) {
		case PartiallyFilled:
		case Filled:
			switch (order.getDirection()) {
			case Long:
				setCurrentQty(getCurrentQty() + qty.getFilledQty() - qty.getLastFilledQty());
				break;
			case Short:
				setCurrentQty(getCurrentQty() - qty.getFilledQty() + qty.getLastFilledQty());
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
