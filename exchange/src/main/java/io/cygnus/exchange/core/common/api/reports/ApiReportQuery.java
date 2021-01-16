package io.cygnus.exchange.core.common.api.reports;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public final class ApiReportQuery {

	public long timestamp;

	// transfer unique id
	// can be constant unless going to push data concurrently
	public final int transferId;

	// serializable object
	public final ReportQuery<?> query;

	@Override
	public String toString() {
		return "[REPORT_QUERY tid=" + transferId + " query=" + query + "]";
	}

}
