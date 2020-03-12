package io.mercury.example;

import org.eclipse.collections.api.map.MutableMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.concurrent.disruptor.BufferSize;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.LogLevel;
import io.mercury.common.log.LoggerSetter;
import io.mercury.common.param.ParamKeyMap;
import io.mercury.ctp.adaptor.CtpAdaptorParams;
import io.mercury.ctp.adaptor.CtpInboundAdaptor;
import io.mercury.ctp.adaptor.CtpOutboundAdaptor;
import io.mercury.polaris.financial.instrument.Instrument.PriorityCloseType;
import io.mercury.polaris.financial.instrument.InstrumentKeeper;
import io.mercury.polaris.financial.instrument.futures.ChinaFutures;
import io.mercury.polaris.financial.instrument.futures.ChinaFuturesSymbol;
import io.mercury.polaris.financial.time.TimePeriodPool;
import io.mercury.polaris.financial.time.TradingPeriodPool;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.engine.actor.AppGlobalStatus;
import io.redstone.engine.scheduler.SpscQueueStrategyScheduler;
import io.redstone.engine.storage.AdaptorKeeper;
import io.redstone.engine.storage.StrategyKeeper;

public final class StartExample {

	private static int appId = 1;

	public static void main(String[] args) {
		long datetime = DateTimeUtil.datetimeOfSecond();
		LoggerSetter.logFileName("redstone-" + appId + "-" + datetime);
		LoggerSetter.logLevel(LogLevel.DEBUG);

		// Set Global AppId
		AppGlobalStatus.setAppId(appId);

		StrategyScheduler scheduler = new SpscQueueStrategyScheduler(BufferSize.POW2_12);

		// Adaptor Params
		MutableMap<CtpAdaptorParams, Object> paramMap = MutableMaps.newUnifiedMap();
		paramMap.put(CtpAdaptorParams.CTP_Trader_Address, "tcp://180.168.146.187:10000");
		paramMap.put(CtpAdaptorParams.CTP_Md_Address, "tcp://180.168.146.187:10010");
		paramMap.put(CtpAdaptorParams.CTP_BrokerId, "9999");
		paramMap.put(CtpAdaptorParams.CTP_InvestorId, "005853");
		paramMap.put(CtpAdaptorParams.CTP_AccountId, "005853");
		paramMap.put(CtpAdaptorParams.CTP_UserId, "005853");
		paramMap.put(CtpAdaptorParams.CTP_Password, "?????");
		ParamKeyMap<CtpAdaptorParams> adaptorParam = new ParamKeyMap<>(() -> paramMap.toImmutable());

		// 创建InboundAdaptor
		int inboundAdaptorId = 1;
		String inboundAdaptorName = "Ctp-InboundAdaptor";
		CtpInboundAdaptor inboundAdaptor = new CtpInboundAdaptor(inboundAdaptorId, inboundAdaptorName, scheduler,
				adaptorParam);

		// 创建OutboundAdaptor
		int outboundAdaptorId = 2;
		String outboundAdaptorName = "Ctp-InboundAdaptor";
		CtpOutboundAdaptor outboundAdaptor = new CtpOutboundAdaptor(outboundAdaptorId, outboundAdaptorName,
				inboundAdaptor.getGateway());

		TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), TimePeriod.values());

		TradingPeriodPool.Singleton.register(ChinaFuturesSymbol.values());

		ChinaFutures rb1910 = ChinaFutures.build(ChinaFuturesSymbol.RB, 1910, PriorityCloseType.BEFORE_TODAY);

		InstrumentKeeper.putInstrument(rb1910);

		int strategyId = 1;
		int subAccountId = 1;

		SmaStrategyExample example = new SmaStrategyExample(strategyId, subAccountId, rb1910);
		example.initialize(() -> true);

		StrategyKeeper.putStrategy(example);
		AdaptorKeeper.putOutboundAdaptor(subAccountId, outboundAdaptor);

		inboundAdaptor.activate();
		outboundAdaptor.subscribeMarketData(SubscribeMarketData.build(rb1910));
	}

}
