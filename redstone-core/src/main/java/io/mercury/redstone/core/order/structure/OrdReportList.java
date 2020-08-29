package io.mercury.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;

@Deprecated
public final class OrdReportList {

	private long uniqueId;

	private MutableList<OrdReport> allReport = MutableLists.newFastList(8);

	public OrdReportList(long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public long uniqueId() {
		return uniqueId;
	}

	public MutableList<OrdReport> allReport() {
		return allReport;
	}

	public boolean isEmpty() {
		return allReport.isEmpty();
	}

	public Optional<OrdReport> first() {
		return allReport.getFirstOptional();
	}

	public Optional<OrdReport> last() {
		return allReport.getLastOptional();
	}

	public void add(OrdReport ordReport) {
		allReport.add(ordReport);
	}

}
