package io.mercury.redstone.launch;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.slf4j.Logger;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;
import io.mercury.common.param.ImmutableParams;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.instrument.futures.impl.ChinaFutures;
import io.mercury.financial.instrument.futures.impl.ChinaFuturesSymbol;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.time.TimePeriodPool;
import io.mercury.financial.time.TradingPeriodPool;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.ftdc.adaptor.FtdcAdaptor;
import io.mercury.ftdc.adaptor.FtdcAdaptorParamKey;
import io.mercury.redstone.core.adaptor.Adaptor;
import io.mercury.redstone.core.strategy.StrategyScheduler;
import io.mercury.redstone.engine.scheduler.SingleStrategyScheduler;
import io.mercury.redstone.strategy.SmaStrategyExample;

public final class ExampleWithProperties {

	private static final Logger log = CommonLoggerFactory.getLogger(ExampleWithProperties.class);

	static {
		long datetime = DateTimeUtil.datetimeOfSecond();
		LogConfigurator.logFileName("redstone-example-" + datetime);
		LogConfigurator.logLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {

		// TODO 读取配置文件
		Properties prop = null;
		int strategyId = 1;
		int subAccountId = 1;
		ChinaFutures rb2010 = new ChinaFutures(ChinaFuturesSymbol.RB, 2010);
		InstrumentManager.initialize(rb2010);

		log.info("read properties -> {}", prop);

		SmaStrategyExample strategyExample = new SmaStrategyExample(strategyId, subAccountId, rb2010, null);
		StrategyScheduler<BasicMarketData> scheduler = new SingleStrategyScheduler<>(strategyExample);
		strategyExample.initialize(() -> true);

		// Set Global AppId
		ImmutableParams<FtdcAdaptorParamKey> adaptorParams = new ImmutableParams<>(FtdcAdaptorParamKey.values(), prop);

		// 创建InboundAdaptor
		int adaptorId = 1;

		// TODO ADD ACCOUNT
		try (Adaptor adaptor = new FtdcAdaptor(adaptorId, null, scheduler, adaptorParams)) {

			TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), TimePeriod.newWith(Duration.ofSeconds(15)));
			TradingPeriodPool.Singleton.register(ChinaFuturesSymbol.values());

			adaptor.startup();

		} catch (IOException e) {
			log.error("IOException -> {}", e.getMessage(), e);
		}

	}

}
