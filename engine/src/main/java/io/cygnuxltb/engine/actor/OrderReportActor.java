package io.cygnuxltb.engine.actor;

import io.horizon.trader.handler.OrderReportHandler;
import io.horizon.trader.transport.outbound.TdxOrderReport;

import javax.annotation.Nonnull;

public class OrderReportActor
        implements OrderReportHandler {


    @Override
    public void onOrderReport(@Nonnull TdxOrderReport report) {
        // TODO Auto-generated method stub

    }

}
