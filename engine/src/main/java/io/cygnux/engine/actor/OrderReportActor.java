package io.cygnux.engine.actor;

import io.horizon.trader.handler.OrderReportHandler;
import io.horizon.trader.transport.outbound.OrderReport;
import io.mercury.actors.BaseActorT1;

import javax.annotation.Nonnull;

public class OrderReportActor extends BaseActorT1<OrderReport> implements OrderReportHandler {

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
    public void onOrderReport(@Nonnull OrderReport report) {
        // TODO Auto-generated method stub

    }

}
