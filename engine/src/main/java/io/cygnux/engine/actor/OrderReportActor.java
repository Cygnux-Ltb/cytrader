package io.cygnux.engine.actor;

import io.horizon.trader.handler.OrderReportHandler;
import io.horizon.trader.transport.outbound.DtoOrderReport;
import io.mercury.actors.def.BaseActorT1;

import javax.annotation.Nonnull;

public class OrderReportActor extends BaseActorT1<DtoOrderReport> implements OrderReportHandler {

    @Override
    protected Class<DtoOrderReport> eventType() {
        return DtoOrderReport.class;
    }

    @Override
    protected void onEvent(DtoOrderReport t) {
        onOrderReport(t);
    }

    @Override
    protected void handleUnknown0(Object t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onOrderReport(@Nonnull DtoOrderReport report) {
        // TODO Auto-generated method stub

    }

}
