package io.redstone.engine.actor;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.impl.OrderReport;
import io.redstone.engine.storage.OrderKeeper;
import io.redstone.engine.storage.StrategyKeeper;

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
		StrategyKeeper.getStrategys(marketData.getInstrument().instrumentId()).forEach(strategy -> {
			if (strategy.isEnabled())
				strategy.onMarketData(marketData);
		});
	}

	public void onOrderReport(OrderReport orderReport) {
		logger.info("Handle OrderReport, brokerRtnId==[{}], ordSysId==[{}]", orderReport.getBrokerRtnId(),
				orderReport.getOrdSysId());
		Order order = OrderKeeper.getOrder(orderReport.getOrdSysId());
		logger.info("Search Order OK. BrokerRtnId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
				order.strategyId(), order.instrument().instrumentCode(), orderReport.getOrdSysId());
		StrategyKeeper.getStrategy(order.strategyId()).onOrder(order);
	}

}
