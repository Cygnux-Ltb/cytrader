package io.mercury.redstone.core.order.structure;

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



}
