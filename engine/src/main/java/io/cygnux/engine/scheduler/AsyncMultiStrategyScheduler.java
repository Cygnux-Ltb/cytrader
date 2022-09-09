package io.cygnux.engine.scheduler;

import java.io.IOException;

import io.cygnux.engine.trader.OrderKeeper;
import org.slf4j.Logger;

import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.transport.outbound.DtoAdaptorReport;
import io.horizon.trader.transport.outbound.DtoOrderReport;
import io.mercury.common.collections.Capacity;
import io.mercury.common.concurrent.queue.jct.JctSingleConsumerQueue;
import io.mercury.common.log.Log4j2LoggerFactory;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class AsyncMultiStrategyScheduler<M extends MarketData> extends AbstractMultiStrategyScheduler<M> {

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(AsyncMultiStrategyScheduler.class);

    private final JctSingleConsumerQueue<QueueMsg> queue;

    private static final int MarketData = 0;
    private static final int OrderReport = 1;
    private static final int AdaptorEvent = 2;

    public AsyncMultiStrategyScheduler(Capacity capacity) {
        this.queue = JctSingleConsumerQueue.spscQueue("AsyncMultiStrategyScheduler-Queue")
                .setCapacity(capacity.value()).useSpinStrategy().process(msg -> {
                    switch (msg.getMark()) {
                        case MarketData -> {
                            M marketData = msg.getMarketData();
                            MarketDataKeeper.onMarketDate(marketData);
                            subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
                                if (strategy.isEnabled()) {
                                    strategy.onMarketData(marketData);
                                }
                            });
                        }
                        case OrderReport -> {
                            DtoOrderReport report = msg.getOrderReport();
                            log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", report.getBrokerOrdSysId(),
                                    report.getOrdSysId());
                            ChildOrder order = OrderKeeper.handleOrderReport(report);
                            log.info(
                                    "Search Order OK. brokerSysId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
                                    report.getBrokerOrdSysId(), order.getStrategyId(),
                                    order.getInstrument().getInstrumentCode(), report.getOrdSysId());
                            strategyMap.get(order.getStrategyId()).onOrder(order);
                        }
                        case AdaptorEvent -> {
                            DtoAdaptorReport adaptorReport = msg.getAdaptorReport();
                            adaptorReport.getAdaptorId();
                            log.info("Recv AdaptorEvent -> {}", adaptorReport);
                        }
                        default -> throw new IllegalStateException("scheduler mark illegal");
                    }
                });
    }

    // TODO add pools
    @Override
    public void onMarketData(@Nonnull M marketData) {
        queue.enqueue(new QueueMsg(marketData));
    }

    // TODO add pools
    @Override
    public void onOrderReport(@Nonnull DtoOrderReport report) {
        queue.enqueue(new QueueMsg(report));
    }

    // TODO add pools
    @Override
    public void onAdaptorReport(@Nonnull DtoAdaptorReport report) {
        queue.enqueue(new QueueMsg(report));
    }

    private class QueueMsg {

        private final int mark;

        private M marketData;

        private DtoOrderReport orderReport;

        private DtoAdaptorReport adaptorReport;

        private QueueMsg(M marketData) {
            this.mark = MarketData;
            this.marketData = marketData;
        }

        private QueueMsg(DtoOrderReport orderReport) {
            this.mark = OrderReport;
            this.orderReport = orderReport;
        }

        private QueueMsg(DtoAdaptorReport adaptorReport) {
            this.mark = AdaptorEvent;
            this.adaptorReport = adaptorReport;
        }

        public int getMark() {
            return mark;
        }

        public M getMarketData() {
            return marketData;
        }

        public DtoOrderReport getOrderReport() {
            return orderReport;
        }

        public DtoAdaptorReport getAdaptorReport() {
            return adaptorReport;
        }

    }

    @Override
    protected void close0() throws IOException {
        // TODO Auto-generated method stub

    }

}
