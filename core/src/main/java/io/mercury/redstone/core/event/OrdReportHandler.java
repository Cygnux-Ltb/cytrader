package io.mercury.redstone.core.event;

import io.mercury.redstone.core.order.structure.OrdReport;

@FunctionalInterface
public interface OrdReportHandler {

	void onOrdReport(OrdReport ordReport);

}
