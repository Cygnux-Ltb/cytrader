package io.mercury.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;

public final class TrdRecordList {

	private long uniqueId;

	private MutableList<TrdRecord> allRecord = MutableLists.newFastList(8);

	public TrdRecordList(long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public long uniqueId() {
		return uniqueId;
	}

	public MutableList<TrdRecord> allRecord() {
		return allRecord;
	}

	public boolean isEmpty() {
		return allRecord.isEmpty();
	}

	public Optional<TrdRecord> first() {
		return allRecord.getFirstOptional();
	}

	public Optional<TrdRecord> last() {
		return allRecord.getLastOptional();
	}

	private int serial = -1;

	public void add(long epochTime, long trdPrice, int trdQty) {
		allRecord.add(new TrdRecord(++serial, uniqueId, epochTime, trdPrice, trdQty));
	}

}
