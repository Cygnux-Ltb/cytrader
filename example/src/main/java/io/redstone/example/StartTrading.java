package io.redstone.example;

import org.eclipse.collections.api.map.MutableMap;

import io.ffreedom.common.collections.MutableMaps;
import io.ffreedom.common.concurrent.disruptor.BufferSize;
import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.log.LogLevel;
import io.ffreedom.common.log.LoggerSetter;
import io.ffreedom.common.param.ParamKeyMap;
import io.polaris.datetime.TimePeriodPool;
import io.polaris.datetime.TradingPeriodPool;
import io.polaris.financial.Instrument.PriorityCloseType;
import io.polaris.financial.futures.ChinaFutures;
import io.polaris.financial.futures.ChinaFuturesSymbol;
import io.polaris.indicators.api.IndicatorTimePeriod;
import io.redstone.actor.AppGlobalStatus;
import io.redstone.adaptor.jctp.JctpAdaptorParams;
import io.redstone.adaptor.jctp.JctpInboundAdaptor;
import io.redstone.adaptor.jctp.JctpOutboundAdaptor;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.scheduler.SpscStrategyScheduler;
import io.redstone.storage.AdaptorKeeper;
import io.redstone.storage.InstrumentKeeper;
import io.redstone.storage.StrategyKeeper;

public final class StartTrading {

	private static int appId = 1;

	public static void main(String[] args) {
		long datetime = DateTimeUtil.datetimeToSecond();
		LoggerSetter.setLogFileName("redstone-" + appId + "-" + datetime);
		LoggerSetter.setLogLevel(LogLevel.DEBUG);

		// Set Global AppId
		AppGlobalStatus.setAppId(appId);

		StrategyScheduler scheduler = new SpscStrategyScheduler(BufferSize.POW2_12);

		// Adaptor Params
		MutableMap<JctpAdaptorParams, Object> paramMap = MutableMaps.newUnifiedMap();
		paramMap.put(JctpAdaptorParams.CTP_Trader_Address, "tcp://180.168.146.187:10000");
		paramMap.put(JctpAdaptorParams.CTP_Md_Address, "tcp://180.168.146.187:10010");
		paramMap.put(JctpAdaptorParams.CTP_BrokerId, "9999");
		paramMap.put(JctpAdaptorParams.CTP_InvestorId, "005853");
		paramMap.put(JctpAdaptorParams.CTP_AccountId, "005853");
		paramMap.put(JctpAdaptorParams.CTP_UserId, "005853");
		paramMap.put(JctpAdaptorParams.CTP_Password, "jinpengpass101");
		ParamKeyMap<JctpAdaptorParams> adaptorParam = new ParamKeyMap<>(() -> paramMap.toImmutable());

		// 创建InboundAdaptor
		int inboundAdaptorId = 1;
		String inboundAdaptorName = "Ctp-InboundAdaptor";
		JctpInboundAdaptor inboundAdaptor = new JctpInboundAdaptor(inboundAdaptorId, inboundAdaptorName, scheduler,
				adaptorParam);

		// 创建OutboundAdaptor
		int outboundAdaptorId = 2;
		String outboundAdaptorName = "Ctp-InboundAdaptor";
		JctpOutboundAdaptor outboundAdaptor = new JctpOutboundAdaptor(outboundAdaptorId, outboundAdaptorName,
				inboundAdaptor.getJctpGeteway());

		TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), IndicatorTimePeriod.values());

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