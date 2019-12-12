package io.redstone.core.order.structure;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.mercury.common.collections.MutableSets;

public class TradeSet {

	private long ordSysId;
	private int tradingNo = -1;

	private MutableSortedSet<Trade> internalSet = MutableSets.newTreeSortedSet();

	public TradeSet(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public MutableSortedSet<Trade> internalSet() {
		return internalSet;
	}

	public boolean isEmpty() {
		return internalSet.isEmpty();
	}

	public Trade firstTrade() {
		return internalSet.first();
	}

	public Trade lastTrade() {
		return internalSet.last();
	}

	public void addNewTrade(long tradingEpochMillis, double price, long qty) {
		internalSet.add(new Trade(ordSysId, ++tradingNo, tradingEpochMillis, price, qty));
	}

	public class Trade implements Comparable<Trade> {

		private long ordSysId;
		private int tradingNo;
		private long tradingEpochMillis;
		private double tradePrice;
		private long tradeQty;

		public Trade(long ordSysId, int tradingNo, long tradingEpochMillis, double tradePrice, long tradeQty) {
			super();
			this.ordSysId = ordSysId;
			this.tradingNo = tradingNo;
			this.tradingEpochMillis = tradingEpochMillis;
			this.tradePrice = tradePrice;
			this.tradeQty = tradeQty;
		}

		public long getOrdSysId() {
			return ordSysId;
		}

		public int getTradingNo() {
			return tradingNo;
		}

		public long getTradingEpochMillis() {
			return tradingEpochMillis;
		}

		public double getTradePrice() {
			return tradePrice;
		}

		public long getTradeQty() {
			return tradeQty;
		}

		@Override
		public int compareTo(Trade o) {
			return this.tradingNo < o.tradingNo ? -1 : this.tradingNo > o.tradingNo ? 1 : 0;
		}

	}

}
