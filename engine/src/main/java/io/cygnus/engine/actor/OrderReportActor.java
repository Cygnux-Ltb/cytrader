package io.cygnus.engine.actor;

import io.horizon.trader.handler.OrderReportHandler;
import io.horizon.trader.report.OrderReport;
import io.mercury.actors.CommonActorT1;

public class OrderReportActor extends CommonActorT1<OrderReport> implements OrderReportHandler {

	@Override
	protected Class<OrderReport> eventType() {
		return OrderReport.class;
	}

	@Override
	protected void onEvent(OrderReport t) {
		onOrderReport(t);
	}

	@Override
	protected void handleUnknown0(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOrderReport(OrderReport report) {
		// TODO Auto-generated method stub

	}

}
