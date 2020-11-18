package io.cygnus.example.launch;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.slf4j.Logger;

import io.cygnus.engine.scheduler.SingleStrategyScheduler;
import io.cygnus.example.strategy.ExampleSmaStrategy;
import io.horizon.definition.adaptor.Adaptor;
import io.horizon.definition.event.InboundScheduler;
import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.instrument.InstrumentManager;
import io.horizon.definition.market.instrument.futures.impl.ChinaFutures;
import io.horizon.definition.market.instrument.futures.impl.ChinaFuturesSymbol;
import io.horizon.definition.pool.TimePeriodPool;
import io.horizon.definition.pool.TradingPeriodPool;
import io.horizon.ftdc.adaptor.FtdcAdaptor;
import io.horizon.ftdc.adaptor.FtdcAdaptorParamKey;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;
import io.mercury.common.param.ImmutableParams;

public final class ExampleWithProperties {

	public static void main(String[] args) {

		long datetime = DateTimeUtil.datetimeOfSecond();
		LogConfigurator.filename("apollo-example-" + datetime);
		LogConfigurator.logLevel(LogLevel.INFO);

		final Logger log = CommonLoggerFactory.getLogger(ExampleWithProperties.class);

		// TODO 读取配置文件
		Properties prop = null;
		int strategyId = 1;
		int subAccountId = 1;
		ChinaFutures rb2010 = new ChinaFutures(ChinaFuturesSymbol.RB, 2010);
		InstrumentManager.initialize(rb2010);

		log.info("read properties -> {}", prop);

		ExampleSmaStrategy exampleStrategy = new ExampleSmaStrategy(strategyId, subAccountId, rb2010, null);
		InboundScheduler<BasicMarketData> scheduler = new SingleStrategyScheduler<>(exampleStrategy);
		exampleStrategy.initialize(() -> true);

		// Set Global AppId
		ImmutableParams<FtdcAdaptorParamKey> ftdcParam = new ImmutableParams<>(prop, FtdcAdaptorParamKey.values());

		// 创建InboundAdaptor
		int adaptorId = 1;

		// TODO ADD ACCOUNT
		try (Adaptor adaptor = new FtdcAdaptor(adaptorId, null, ftdcParam, scheduler)) {

			TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), Duration.ofSeconds(15));
			TradingPeriodPool.Singleton.register(ChinaFuturesSymbol.values());

			adaptor.startup();

		} catch (IOException e) {
			log.error("IOException -> {}", e.getMessage(), e);
		}

	}

}
