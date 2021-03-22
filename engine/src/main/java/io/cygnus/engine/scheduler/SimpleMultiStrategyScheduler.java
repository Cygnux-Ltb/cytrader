package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.market.data.MarkerDataKeeper;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.order.ChildOrder;
import io.horizon.structure.order.OrderManager;
import io.horizon.structure.order.OrderReport;
import io.mercury.common.log.CommonLoggerFactory;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class SimpleMultiStrategyScheduler<M extends MarketData> extends BaseMultiStrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SimpleMultiStrategyScheduler.class);

	public SimpleMultiStrategyScheduler() {

	}

	@Override
	public void onMarketData(M marketData) {
		MarkerDataKeeper.onMarketDate(marketData);
		subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
			if (strategy.isEnabled()) {
				strategy.onMarketData(marketData);
			}
		});
	}

	@Override
	public void onOrderReport(OrderReport report) {
		log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", report.getBrokerUniqueId(),
				report.getOrdSysId());
		ChildOrder order = OrderManager.handleOrderReport(report);
		log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
				report.getBrokerUniqueId(), order.getStrategyId(), order.getInstrument().getInstrumentCode(),
				report.getOrdSysId());
		strategyMap.get(order.getStrategyId()).onOrder(order);
	}

	// TODO add pools
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.info("Recv AdaptorEvent -> {}", event);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

}
