package io.cygnus.example.launch;

import java.time.Duration;
import java.util.Properties;

import org.slf4j.Logger;

import io.cygnus.engine.scheduler.SimpleSingleStrategyScheduler;
import io.cygnus.example.strategy.ExampleSmaStrategy;
import io.horizon.ftdc.adaptor.FtdcAdaptor;
import io.horizon.ftdc.adaptor.FtdcAdaptorParamKey;
import io.horizon.structure.adaptor.Adaptor;
import io.horizon.structure.event.InboundScheduler;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.InstrumentKeeper;
import io.horizon.structure.market.instrument.impl.ChinaFutures;
import io.horizon.structure.market.instrument.impl.ChinaFuturesSymbol;
import io.horizon.structure.pool.TimePeriodPool;
import io.horizon.structure.pool.TradablePeriodPool;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;
import io.mercury.common.param.ImmutableParams;

public class ExampleWithNacos {

	static {
		LogConfigurator.setFilename("cygnus-example-" + DateTimeUtil.datetimeOfSecond());
		LogConfigurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {

		Logger log = CommonLoggerFactory.getLogger(ExampleWithNacos.class);

		log.info("Start example");

		// TODO 读取配置文件
		Properties prop = null;
		int strategyId = 1;
		int subAccountId = 1;
		ChinaFutures rb2010 = new ChinaFutures(ChinaFuturesSymbol.RB, 2010);
		InstrumentKeeper.initialize(rb2010);

		ExampleSmaStrategy exampleStrategy = new ExampleSmaStrategy(strategyId, subAccountId,  null, rb2010);
		InboundScheduler<BasicMarketData> scheduler = new SimpleSingleStrategyScheduler<>(exampleStrategy);
		exampleStrategy.initialize(() -> true);

		// Set Global AppId
		ImmutableParams<FtdcAdaptorParamKey> ftdcParam = new ImmutableParams<>(prop, FtdcAdaptorParamKey.values());

		// 创建InboundAdaptor
		int adaptorId = 1;

		// TODO ADD ACCOUNT
		try (Adaptor adaptor = new FtdcAdaptor(adaptorId, null, ftdcParam, scheduler)) {

			TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), Duration.ofSeconds(15));
			TradablePeriodPool.Singleton.register(ChinaFuturesSymbol.values());

			adaptor.startup();

		} catch (Exception e) {
			log.error("adaptor throws Exception -> {}", e.getMessage(), e);
		}

	}

}
