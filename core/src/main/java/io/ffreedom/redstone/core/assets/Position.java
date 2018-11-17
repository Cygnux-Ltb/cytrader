package io.ffreedom.redstone.core.assets;

import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.financial.Instrument;
import io.ffreedom.financial.Instrument.PriorityCloseType;
import io.ffreedom.redstone.core.order.enums.OrdSide;

public final class Position implements Comparable<Position> {

	private int instrumentId;
	private double currentQty;
	private double availableQty;

	private double beforeTodayQty;
	private MutableLongDoubleMap beforeTodayQtyLockRecord;

	public final static Position EMPTY = new Position(-1, PriorityCloseType.NONE);

	private Position(int instrumentId, PriorityCloseType priorityCloseType) {
		this.instrumentId = instrumentId;
		if (priorityCloseType == PriorityCloseType.BEFORE_TODAY)
			beforeTodayQtyLockRecord = EclipseCollections.newLongDoubleHashMap(16);
	}

	private Position(int instrumentId, PriorityCloseType priorityCloseType, double beforeTodayQty) {
		this(instrumentId, priorityCloseType);
		initBeforeTodayQty(beforeTodayQty);
	}

	private void initBeforeTodayQty(double beforeTodayQty) {
		this.beforeTodayQty = beforeTodayQty;
	}

	public final static Position newInstance(Instrument instrument) {
		return new Position(instrument.getInstrumentId(), instrument.getPriorityCloseType());
	}

	public final static Position newInstance(Instrument instrument, double beforeTodayQty) {
		return new Position(instrument.getInstrumentId(), instrument.getPriorityCloseType(), beforeTodayQty);
	}

	public Position setCurrentQty(double currentQty) {
		this.currentQty = currentQty;
		return this;
	}

	public Position setAvailableQty(double availableQty) {
		this.availableQty = availableQty;
		return this;
	}

	public int getInstrumentId() {
		return instrumentId;
	}

	public double getCurrentQty() {
		return currentQty;
	}

	public double getBeforeTodayQty() {
		return beforeTodayQty;
	}

	public double getAvailableQty() {
		return availableQty;
	}

	public double lockBeforeTodayQty(long ordSysId, OrdSide side, double offerQty) {
		if (beforeTodayQty == 0) {
			return 0;
		} else if (beforeTodayQty > 0) {
			switch (side.direction()) {
			case Short:
				// 需要锁定的Qty小于或等于昨仓,实际锁定量等于请求量
				if (offerQty <= beforeTodayQty) {
					// 记录此订单锁定量
					beforeTodayQtyLockRecord.put(ordSysId, offerQty);
					beforeTodayQty -= offerQty;
					return offerQty;
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
				if (offerQty <= absBeforeTodayQty) {
					// 记录此订单锁定量
					beforeTodayQtyLockRecord.put(ordSysId, offerQty);
					beforeTodayQty += offerQty;
					return offerQty;
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

	@Override
	public int compareTo(Position o) {
		return this.instrumentId < o.instrumentId ? -1 : this.instrumentId > o.instrumentId ? 1 : 0;
	}

	public static void main(String[] args) {

		System.out.println(-10 - 5);

	}

}
