package io.redstone.core.order.structure;

import java.util.Optional;

import org.eclipse.collections.api.list.MutableList;

import io.mercury.common.collections.MutableLists;

public final class OrdReportSet {

	private long ordSysId;

	private MutableList<OrdReport> allReport = MutableLists.newFastList(8);

	public OrdReportSet(long ordSysId) {
		this.ordSysId = ordSysId;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public MutableList<OrdReport> allReport() {
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

	public void addReport(OrdReport ordReport) {
		allReport.add(ordReport);
	}

}
