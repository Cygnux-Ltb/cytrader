package io.mercury.redstone.launch;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.LogConfigurator;
import io.mercury.common.log.LogConfigurator.LogLevel;
import io.mercury.common.param.map.ImmutableParamMap;
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

public final class StartExample {

	public static void main(String[] args) {
		long datetime = DateTimeUtil.datetimeOfSecond();
		LogConfigurator.logFileName("redstone-example-" + datetime);
		LogConfigurator.logLevel(LogLevel.INFO);

		// TODO 读取配置文件
		Properties properties = null;
		int strategyId = 1;
		int subAccountId = 1;
		ChinaFutures rb2010 = new ChinaFutures(ChinaFuturesSymbol.RB, 2010);
		InstrumentManager.initialize(rb2010);

		SmaStrategyExample strategyExample = new SmaStrategyExample(strategyId, subAccountId, rb2010);
		StrategyScheduler<BasicMarketData> scheduler = new SingleStrategyScheduler<>(strategyExample);
		strategyExample.initialize(() -> true);

		// Set Global AppId

		ImmutableParamMap<FtdcAdaptorParamKey> adaptorParam = new ImmutableParamMap<>(FtdcAdaptorParamKey.values(),
				properties);

		// 创建InboundAdaptor
		int inboundAdaptorId = 1;
		String inboundAdaptorName = "Ctp-InboundAdaptor";
		// TODO ADD ACCOUNT

		try (Adaptor adaptor = new FtdcAdaptor(inboundAdaptorId, inboundAdaptorName, null, scheduler, adaptorParam)) {

			TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), TimePeriod.newWith(Duration.ofSeconds(15)));
			TradingPeriodPool.Singleton.register(ChinaFuturesSymbol.values());

			adaptor.startup();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
