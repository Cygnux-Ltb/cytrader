package io.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;
import io.mercury.polaris.financial.instrument.Instrument.MarketConstant;

public final class TradeList {

	private long ordSysId;
	private int serial = -1;

	private MutableList<Trade> internalList = MutableLists.newFastList(8);

	public TradeList(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public MutableList<Trade> internalList() {
		return internalList;
	}

	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	public Optional<Trade> firstTrade() {
		return internalList.getFirstOptional();
	}

	public Optional<Trade> lastTrade() {
		return internalList.getLastOptional();
	}

	public void addNewTrade(long epochTime, long tradePrice, long qty) {
		internalList.add(new Trade(++serial, ordSysId, epochTime, tradePrice, qty));
	}

	/**
	 * tradePrice fix use {@link MarketConstant#PriceMultiplier}
	 */
	public static class Trade implements Comparable<Trade> {

		private int serial;
		private long ordSysId;
		private long epochTime;

		private long tradePrice;
		private long tradeQty;

		public Trade(int serial, long ordSysId, long epochTime, long tradePrice, long tradeQty) {
			super();
			this.ordSysId = ordSysId;
			this.serial = serial;
			this.epochTime = epochTime;
			this.tradePrice = tradePrice;
			this.tradeQty = tradeQty;
		}

		public long ordSysId() {
			return ordSysId;
		}

		public int serial() {
			return serial;
		}

		public long epochTime() {
			return epochTime;
		}

		public long tradePrice() {
			return tradePrice;
		}

		public long tradeQty() {
			return tradeQty;
		}

		@Override
		public int compareTo(Trade o) {
			return this.serial < o.serial ? -1 : this.serial > o.serial ? 1 : 0;
		}

	}

}
