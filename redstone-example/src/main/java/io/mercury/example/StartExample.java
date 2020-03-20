package io.mercury.example;

import java.util.Properties;

import org.eclipse.collections.api.map.MutableMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.concurrent.disruptor.BufferSize;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.LogLevel;
import io.mercury.common.log.LoggerSetter;
import io.mercury.common.param.ImmutableParamMap;
import io.mercury.ctp.adaptor.CtpAdaptor;
import io.mercury.ctp.adaptor.CtpAdaptorParam;
import io.mercury.financial.instrument.InstrumentKeeper;
import io.mercury.financial.instrument.Instrument.PriorityCloseType;
import io.mercury.financial.instrument.futures.ChinaFutures;
import io.mercury.financial.instrument.futures.ChinaFuturesSymbol;
import io.mercury.financial.time.TimePeriodPool;
import io.mercury.financial.time.TradingPeriodPool;
import io.mercury.financial.vector.TimePeriod;
import io.redstone.core.adaptor.Adaptor;
import io.redstone.core.adaptor.AdaptorKeeper;
import io.redstone.core.strategy.StrategyKeeper;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.engine.actor.AppGlobalStatus;
import io.redstone.engine.config.NacosReader;
import io.redstone.engine.scheduler.SpscQueueStrategyScheduler;

public final class StartExample {

	private static int appId = 1;

	public static void main(String[] args) {
		long datetime = DateTimeUtil.datetimeOfSecond();
		LoggerSetter.logFileName("redstone-" + appId + "-" + datetime);
		LoggerSetter.logLevel(LogLevel.DEBUG);

		Properties properties = NacosReader.readWith(args);

		// Set Global AppId
		AppGlobalStatus.setAppId(appId);

		StrategyScheduler scheduler = new SpscQueueStrategyScheduler(BufferSize.POW2_12);

		// Adaptor Params
		MutableMap<CtpAdaptorParam, Object> paramMap = MutableMaps.newUnifiedMap();
		paramMap.put(CtpAdaptorParam.CTP_Trader_Address, "tcp://180.168.146.187:10000");
		paramMap.put(CtpAdaptorParam.CTP_Md_Address, "tcp://180.168.146.187:10010");
		paramMap.put(CtpAdaptorParam.CTP_BrokerId, "9999");
		paramMap.put(CtpAdaptorParam.CTP_InvestorId, "005853");
		paramMap.put(CtpAdaptorParam.CTP_AccountId, "005853");
		paramMap.put(CtpAdaptorParam.CTP_UserId, "005853");
		paramMap.put(CtpAdaptorParam.CTP_Password, "?????");
		ImmutableParamMap<CtpAdaptorParam> adaptorParam = new ImmutableParamMap<>(properties);

		// 创建InboundAdaptor
		int inboundAdaptorId = 1;
		String inboundAdaptorName = "Ctp-InboundAdaptor";
		Adaptor adaptor = new CtpAdaptor(inboundAdaptorId, inboundAdaptorName, appId, scheduler,
				adaptorParam);

		

		TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), TimePeriod.values());

		TradingPeriodPool.Singleton.register(ChinaFuturesSymbol.values());

		ChinaFutures rb1910 = ChinaFutures.build(ChinaFuturesSymbol.RB, 1910, PriorityCloseType.BEFORE_TODAY);

		InstrumentKeeper.putInstrument(rb1910);

		int strategyId = 1;
		int subAccountId = 1;

		SmaStrategyExample example = new SmaStrategyExample(strategyId, subAccountId, rb1910);
		example.initialize(() -> true);

		StrategyKeeper.putStrategy(example);
		AdaptorKeeper.putAdaptor(subAccountId, adaptor);

		adaptor.startup();
		
	}

}
