package io.ffreedom.redstone.launch;

import org.eclipse.collections.api.map.MutableMap;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.config.FromPropertiesFile;
import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.log.LogLevel;
import io.ffreedom.common.log.LoggerSetter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.polaris.financial.Instrument.PriorityCloseType;
import io.ffreedom.polaris.financial.futures.ChinaFutures;
import io.ffreedom.polaris.financial.futures.ChinaFuturesSymbol;
import io.ffreedom.polaris.indicators.api.IndicatorPeriod;
import io.ffreedom.polaris.indicators.pools.TimeTwinPools;
import io.ffreedom.redstone.actor.AppGlobalStatus;
import io.ffreedom.redstone.actor.InstrumentKeeper;
import io.ffreedom.redstone.adaptor.jctp.JctpAdaptorParams;
import io.ffreedom.redstone.adaptor.jctp.JctpInboundAdaptor;
import io.ffreedom.redstone.adaptor.jctp.JctpOutboundAdaptor;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;
import io.ffreedom.redstone.scheduler.SPSCStrategyScheduler;

public class Saturn5 {

//	private static StringBuilder printInfo = new StringBuilder().append("Param error...." + SysCharacter.LINE_SEPARATOR)
//			.append("Param List is ->" + SysCharacter.LINE_SEPARATOR)
//			.append("Param 1 : ApplicationId." + SysCharacter.LINE_SEPARATOR);

	public static void main(String[] args) {
//		if (args.length < 1) {
//			System.out.println(printInfo);
//			throw new RuntimeException("param error.");
//		}
		int appId = FromPropertiesFile.getIntApplicationProperty("appId");

		// Set Global AppId
		AppGlobalStatus.setAppId(appId);
		long datetime = DateTimeUtil.datetimeToSecond();

		LoggerSetter.setLogFileName("redstone-" + appId + "-" + datetime);
		LoggerSetter.setLogLevel(LogLevel.DEBUG);

		TimeTwinPools.register(ChinaFuturesSymbol.values(), IndicatorPeriod.M1);
		StrategyScheduler scheduler = new SPSCStrategyScheduler(2048);

		ChinaFutures futures = ChinaFutures.build(ChinaFuturesSymbol.RB, 1910, PriorityCloseType.BEFORE_TODAY);
		InstrumentKeeper.putInstrument(futures);

		// Adaptor Params
		MutableMap<JctpAdaptorParams, Object> paramMap = ECollections.newUnifiedMap();
		paramMap.put(JctpAdaptorParams.CTP_Trader_Address, "tcp://180.168.146.187:10000");
		paramMap.put(JctpAdaptorParams.CTP_Md_Address, "tcp://180.168.146.187:10010");
		paramMap.put(JctpAdaptorParams.CTP_BrokerId, "9999");
		paramMap.put(JctpAdaptorParams.CTP_InvestorId, "005853");
		paramMap.put(JctpAdaptorParams.CTP_AccountId, "005853");
		paramMap.put(JctpAdaptorParams.CTP_UserId, "005853");
		paramMap.put(JctpAdaptorParams.CTP_Password, "jinpengpass101");

		ParamMap<JctpAdaptorParams> adaptorParam = new ParamMap<>(() -> paramMap.toImmutable());

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

		inboundAdaptor.activate();

		SubscribeMarketData subscribeMarketData = SubscribeMarketData.build(futures);

		outboundAdaptor.subscribeMarketData(subscribeMarketData);

	}

}
