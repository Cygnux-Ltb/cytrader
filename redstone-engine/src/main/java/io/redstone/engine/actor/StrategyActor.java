package io.redstone.engine.actor;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderKeeper;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyKeeper;

/**
 * 管理全部策略<br>
 * 1负责调用行情事件与订单回报事件<br>
 * 2...<br>
 * 
 * @author yellow013
 */
public final class StrategyActor {

	private Logger log = CommonLoggerFactory.getLogger(StrategyActor.class);

	public static final StrategyActor Singleton = new StrategyActor();

	private StrategyActor() {

	}

	@Deprecated
	public void onMarketData(BasicMarketData marketData) {
		StrategyKeeper.getSubscribedStrategys(marketData.instrument().id()).forEach(strategy -> {
			if (strategy.isEnabled())
				strategy.onMarketData(marketData);
		});
	}

	@Deprecated
	public void onOrderReport(OrdReport orderReport) {
		log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", orderReport.getBrokerUniqueId(),
				orderReport.getOrdSysId());
		Order order = OrderKeeper.getOrder(orderReport.getOrdSysId());
		log.info("Search Order OK. BrokerRtnId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
				orderReport.getBrokerUniqueId(), order.strategyId(), order.instrument().code(),
				orderReport.getOrdSysId());
		StrategyKeeper.getStrategy(order.strategyId()).onOrder(order);
	}

}
