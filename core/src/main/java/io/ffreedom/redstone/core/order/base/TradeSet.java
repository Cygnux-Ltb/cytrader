package io.ffreedom.redstone.core.order.base;

import java.time.ZonedDateTime;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.ffreedom.common.collect.EclipseCollections;

public class TradeSet {

	private long ordSysId;
	private int tradeSerial = -1;

	private MutableSortedSet<Trade> innerSet = EclipseCollections.newTreeSortedSet();

	public TradeSet(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long getOrdSysId() {
		return ordSysId;
	}

	public MutableSortedSet<Trade> innerSet() {
		return innerSet;
	}

	public boolean isEmpty() {
		return innerSet.isEmpty();
	}

	public Trade firstTrade() {
		return innerSet.first();
	}

	public Trade lastTrade() {
		return innerSet.last();
	}

	public void addNewTrade(ZonedDateTime tradeDateTime, double price, double qty) {
		innerSet.add(new Trade(ordSysId, ++tradeSerial, tradeDateTime, price, qty));
	}

	public class Trade implements Comparable<Trade> {

		private long ordSysId;
		private int tradeSerial;
		private ZonedDateTime tradeDateTime;
		private double tradePrice;
		private double tradeQty;

		public Trade(long ordSysId, int tradeSerial, ZonedDateTime tradeDateTime, double tradePrice, double tradeQty) {
			super();
			this.ordSysId = ordSysId;
			this.tradeSerial = tradeSerial;
			this.tradeDateTime = tradeDateTime;
			this.tradePrice = tradePrice;
			this.tradeQty = tradeQty;
		}

		public long getOrdSysId() {
			return ordSysId;
		}

		public int getTradeSerial() {
			return tradeSerial;
		}

		public ZonedDateTime getTradeDateTime() {
			return tradeDateTime;
		}

		public double getTradePrice() {
			return tradePrice;
		}

		public double getTradeQty() {
			return tradeQty;
		}

		@Override
		public int compareTo(Trade o) {
			return this.tradeSerial < o.tradeSerial ? -1 : this.tradeSerial > o.tradeSerial ? 1 : 0;
		}

	}

}
