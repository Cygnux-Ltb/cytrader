package io.mercury.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;

public final class OrdReportList {

	private long ordSysId;

	private MutableList<OrdReport> allReport = MutableLists.newFastList(8);

	public OrdReportList(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public MutableList<OrdReport> all() {
		return allReport;
	}

	public boolean isEmpty() {
		return allReport.isEmpty();
	}

	public Optional<OrdReport> firstReport() {
		return allReport.getFirstOptional();
	}

	public Optional<OrdReport> lastReport() {
		return allReport.getLastOptional();
	}

	public void add(OrdReport ordReport) {
		allReport.add(ordReport);
	}

}
