package io.ffreedom.redstone.specific.position;

import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;

import io.ffreedom.common.utils.DoubleUtil;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.base.OrdQtyPrice;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.position.AbsPosition;

public final class FuturesPosition extends AbsPosition {

	private double beforeTodayQty;
	private MutableLongDoubleMap beforeTodayQtyLockRecord;

	public final static FuturesPosition EMPTY = new FuturesPosition(-1, -1);

	private FuturesPosition(int accountId, int instrumentId) {
		this(accountId, instrumentId, 0);
	}

	private FuturesPosition(int accountId, int instrumentId, double beforeTodayQty) {
		super(accountId, instrumentId);
		if (beforeTodayQty != 0)
			initBeforeTodayQty(beforeTodayQty);
	}

	private void initBeforeTodayQty(double beforeTodayQty) {
		this.beforeTodayQty = beforeTodayQty;
	}

	final static FuturesPosition newInstance(int accountId, int instrumentId) {
		return new FuturesPosition(accountId, instrumentId);
	}

	final static FuturesPosition newInstance(int accountId, int instrumentId, double beforeTodayQty) {
		return new FuturesPosition(accountId, instrumentId, beforeTodayQty);
	}

	public double getBeforeTodayQty() {
		return beforeTodayQty;
	}

	public double lockBeforeTodayQty(long ordSysId, OrdSide side, double lockQty) {
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
				double absBeforeTodayQty = Math.abs(beforeTodayQty);
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
				setCurrentQty(
						DoubleUtil.add8(getCurrentQty(), ordQtyPrice.getFilledQty() - ordQtyPrice.getLastFilledQty()));
				break;
			case Short:
				setCurrentQty(DoubleUtil.subtraction(getCurrentQty(),
						ordQtyPrice.getFilledQty() + ordQtyPrice.getLastFilledQty()));
			default:
				break;
			}
			break;
		default:
			break;
		}

	}

}
