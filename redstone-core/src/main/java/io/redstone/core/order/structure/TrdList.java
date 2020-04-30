package io.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;

public final class TrdList {

	private long ordSysId;
	private int serial = -1;

	private MutableList<TrdRecord> records = MutableLists.newFastList(8);

	public TrdList(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public MutableList<TrdRecord> records() {
		return records;
	}

	public boolean isEmpty() {
		return records.isEmpty();
	}

	public Optional<TrdRecord> first() {
		return records.getFirstOptional();
	}

	public Optional<TrdRecord> last() {
		return records.getLastOptional();
	}

	public void addNewRecord(long epochTime, long tradePrice, int qty) {
		records.add(new TrdRecord(++serial, ordSysId, epochTime, tradePrice, qty));
	}

	/**
	 * tradePrice fix use {@link MarketConstant#PriceMultiplier}
	 */
	public static class TrdRecord implements Comparable<TrdRecord> {

		private int serial;
		private long ordSysId;
		private long epochTime;

		private long tradePrice;
		private int tradeQty;

		public TrdRecord(int serial, long ordSysId, long epochTime, long tradePrice, int tradeQty) {
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

		public int tradeQty() {
			return tradeQty;
		}

		@Override
		public int compareTo(TrdRecord o) {
			return this.serial < o.serial ? -1 : this.serial > o.serial ? 1 : 0;
		}

	}

}
