package io.cygnuxltb.engine.actor;

import io.horizon.trader.handler.OrderReportHandler;
import io.horizon.trader.transport.outbound.TdxOrderReport;
import io.mercury.actors.def.BaseActorT1;

import javax.annotation.Nonnull;

public class OrderReportActor extends BaseActorT1<TdxOrderReport> implements OrderReportHandler {

    @Override
    protected Class<TdxOrderReport> eventType() {
        return TdxOrderReport.class;
    }

    @Override
    protected void onEvent(TdxOrderReport t) {
        onOrderReport(t);
    }

    @Override
    protected void handleUnknown0(Object t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onOrderReport(@Nonnull TdxOrderReport report) {
        // TODO Auto-generated method stub

    }

}
