package io.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;
import io.mercury.financial.instrument.Instrument.MarketConstant;

public final class OrdTradeSet {

	private long ordSysId;
	private int serial = -1;

	private MutableList<OrdTrade> allTrade = MutableLists.newFastList(8);

	public OrdTradeSet(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public MutableList<OrdTrade> allOrdTrade() {
		return allTrade;
	}

	public boolean isEmpty() {
		return allTrade.isEmpty();
	}

	public Optional<OrdTrade> firstTrade() {
		return allTrade.getFirstOptional();
	}

	public Optional<OrdTrade> lastTrade() {
		return allTrade.getLastOptional();
	}

	public void addNewTrade(long epochTime, long tradePrice, long qty) {
		allTrade.add(new OrdTrade(++serial, ordSysId, epochTime, tradePrice, qty));
	}

	/**
	 * tradePrice fix use {@link MarketConstant#PriceMultiplier}
	 */
	public static class OrdTrade implements Comparable<OrdTrade> {

		private int serial;
		private long ordSysId;
		private long epochTime;

		private long tradePrice;
		private long tradeQty;

		public OrdTrade(int serial, long ordSysId, long epochTime, long tradePrice, long tradeQty) {
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
		public int compareTo(OrdTrade o) {
			return this.serial < o.serial ? -1 : this.serial > o.serial ? 1 : 0;
		}

	}

}
