package io.ffreedom.redstone.actor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.polaris.market.impl.BasicMarketData;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.impl.OrderReport;
import io.ffreedom.redstone.storage.OrderKeeper;
import io.ffreedom.redstone.storage.StrategyKeeper;

/**
 * 管理全部策略<br>
 * 1负责调用行情事件与订单回报事件<br>
 * 2...<br>
 * 
 * @author yellow013
 */
public final class StrategyActor {

	private Logger logger = CommonLoggerFactory.getLogger(StrategyActor.class);

	public static final StrategyActor Singleton = new StrategyActor();

	private StrategyActor() {

	}

	public void onMarketData(BasicMarketData marketData) {
		StrategyKeeper.getStrategys(marketData.getInstrument().getInstrumentId()).forEach(strategy -> {
			if (strategy.isEnabled())
				strategy.onMarketData(marketData);
		});
	}

	public void onOrderReport(OrderReport orderReport) {
		logger.info("Handle OrderReport, brokerRtnId==[{}], ordSysId==[{}]", orderReport.getBrokerRtnId(),
				orderReport.getOrdSysId());
		Order order = OrderKeeper.getOrder(orderReport.getOrdSysId());
		logger.info("Search Order OK. BrokerRtnId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
				order.getStrategyId(), order.getInstrument().getInstrumentCode(), orderReport.getOrdSysId());
		StrategyKeeper.getStrategy(order.getStrategyId()).onOrder(order);
	}

}
