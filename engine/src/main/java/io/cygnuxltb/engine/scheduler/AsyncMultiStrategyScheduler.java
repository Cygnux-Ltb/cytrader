package io.cygnuxltb.engine.scheduler;

import io.cygnuxltb.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.transport.outbound.TdxAdaptorReport;
import io.horizon.trader.transport.outbound.TdxOrderReport;
import io.mercury.common.collections.Capacity;
import io.mercury.common.concurrent.queue.jct.JctSingleConsumerQueue;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 * <p>
 * 策略执行引擎与整体框架分离
 */
public final class AsyncMultiStrategyScheduler<M extends MarketData> extends AbstractMultiStrategyScheduler<M> {

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
                            TdxOrderReport report = msg.getOrderReport();
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
                            TdxAdaptorReport adaptorReport = msg.getAdaptorReport();
                            String adaptorId = adaptorReport.getAdaptorId();
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
    public void onOrderReport(@Nonnull TdxOrderReport report) {
        queue.enqueue(new QueueMsg(report));
    }

    // TODO add pools
    @Override
    public void onAdaptorReport(@Nonnull TdxAdaptorReport report) {
        queue.enqueue(new QueueMsg(report));
    }

    private class QueueMsg {

        private final int mark;

        private M marketData;

        private TdxOrderReport orderReport;

        private TdxAdaptorReport adaptorReport;

        private QueueMsg(M marketData) {
            this.mark = MarketData;
            this.marketData = marketData;
        }

        private QueueMsg(TdxOrderReport orderReport) {
            this.mark = OrderReport;
            this.orderReport = orderReport;
        }

        private QueueMsg(TdxAdaptorReport adaptorReport) {
            this.mark = AdaptorEvent;
            this.adaptorReport = adaptorReport;
        }

        public int getMark() {
            return mark;
        }

        public M getMarketData() {
            return marketData;
        }

        public TdxOrderReport getOrderReport() {
            return orderReport;
        }

        public TdxAdaptorReport getAdaptorReport() {
            return adaptorReport;
        }

    }

    @Override
    protected void close0() {
        // TODO Auto-generated method stub

    }

}
