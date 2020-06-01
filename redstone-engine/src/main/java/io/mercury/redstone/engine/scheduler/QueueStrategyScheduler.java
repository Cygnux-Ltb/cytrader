package io.mercury.redstone.engine.scheduler;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.concurrent.disruptor.BufferSize;
import io.mercury.common.concurrent.disruptor.SpscQueue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.market.MarkerDataKeeper;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.ActChildOrder;
import io.mercury.redstone.core.order.OrderKeeper;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.Strategy;
import io.mercury.redstone.core.strategy.StrategyScheduler;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class QueueStrategyScheduler implements StrategyScheduler<BasicMarketData> {

	private SpscQueue<DespatchMsg> despatchQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	private static final Logger log = CommonLoggerFactory.getLogger(QueueStrategyScheduler.class);

	/**
	 * 策略列表
	 */
	private final MutableIntObjectMap<Strategy<BasicMarketData>> strategyMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 订阅合约的策略列表
	 */
	private final MutableIntObjectMap<MutableList<Strategy<BasicMarketData>>> subscribedInstrumentMap = MutableMaps
			.newIntObjectHashMap();

	public QueueStrategyScheduler(BufferSize size) {
		this.despatchQueue = new SpscQueue<>("SPSCStrategyScheduler-Queue", size, true, despatchMsg -> {
			switch (despatchMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = despatchMsg.getMarketData();
				MarkerDataKeeper.onMarketDate(marketData);
				subscribedInstrumentMap.get(marketData.instrument().id()).each(strategy -> {
					if (strategy.isEnabled())
						strategy.onMarketData(marketData);
				});
				break;
			case OrderReport:
				OrdReport ordReport = despatchMsg.getOrdReport();
				log.info("Handle OrdReport, brokerUniqueId==[{}], ordSysId==[{}]", ordReport.getBrokerUniqueId(),
						ordReport.getOrdSysId());
				ActChildOrder order = OrderKeeper.onOrdReport(ordReport);
				log.info("Search Order OK. BrokerRtnId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
						ordReport.getBrokerUniqueId(), order.strategyId(), order.instrument().code(),
						ordReport.getOrdSysId());
				strategyMap.get(order.strategyId()).onOrder(order);
				break;
			case AdaptorEvent:
				despatchMsg.getAdaptorEvent();
				break;
			default:
				throw new IllegalStateException("scheduler mark illegal");
			}
		});
	}

	// TODO add pools
	@Override
	public void onMarketData(BasicMarketData marketData) {
		despatchQueue.enqueue(new DespatchMsg(marketData));
	}

	// TODO add pools
	@Override
	public void onOrdReport(OrdReport ordReport) {
		despatchQueue.enqueue(new DespatchMsg(ordReport));
	}

	// TODO add pools
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		despatchQueue.enqueue(new DespatchMsg(event));
	}

	@Override
	public void addStrategy(Strategy<BasicMarketData> strategy) {
		strategyMap.put(strategy.strategyId(), strategy);
		strategy.instruments().each(instrument -> {
			subscribedInstrumentMap.getIfAbsentPut(instrument.id(), MutableLists::newFastList).add(strategy);
			log.info("Add subscribe instrument, strategyId==[{}], instrumentId==[{}]", strategy.strategyId(),
					instrument.id());
		});
		strategy.enable();
	}

	private class DespatchMsg {

		private int mark;
		private BasicMarketData marketData;
		private OrdReport ordReport;
		private AdaptorEvent adaptorEvent;

		DespatchMsg(BasicMarketData marketData) {
			this.mark = MarketData;
			this.marketData = marketData;
		}

		DespatchMsg(OrdReport ordReport) {
			this.mark = OrderReport;
			this.ordReport = ordReport;
		}

		DespatchMsg(AdaptorEvent adaptorEvent) {
			this.mark = AdaptorEvent;
			this.adaptorEvent = adaptorEvent;
		}

		private int mark() {
			return mark;
		}

		private BasicMarketData getMarketData() {
			return marketData;
		}

		private OrdReport getOrdReport() {
			return ordReport;
		}

		private AdaptorEvent getAdaptorEvent() {
			return adaptorEvent;
		}

	}

}
