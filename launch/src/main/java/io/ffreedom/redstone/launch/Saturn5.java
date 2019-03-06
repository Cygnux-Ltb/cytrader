package io.ffreedom.redstone.launch;

import io.ffreedom.common.charset.SysCharacter;
import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.log.LogLevel;
import io.ffreedom.common.log.LoggerSetter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.polaris.financial.futures.ChinaFuturesSymbol;
import io.ffreedom.polaris.indicators.api.IndicatorPeriod;
import io.ffreedom.polaris.indicators.pools.IndicatorPeriodTimePools;
import io.ffreedom.redstone.actor.AppGlobalStatus;
import io.ffreedom.redstone.adaptor.jctp.JctpAdaptorParams;
import io.ffreedom.redstone.adaptor.jctp.JctpInboundAdaptor;
import io.ffreedom.redstone.adaptor.jctp.JctpOutboundAdaptor;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;
import io.ffreedom.redstone.scheduler.SPSCStrategyScheduler;

public class Saturn5 {

	private static StringBuilder printInfo = new StringBuilder().append("Param error...." + SysCharacter.LINE_SEPARATOR)
			.append("Param List is ->" + SysCharacter.LINE_SEPARATOR)
			.append("Param 1 : ApplicationId." + SysCharacter.LINE_SEPARATOR);

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println(printInfo);
			throw new RuntimeException("param error.");
		}
		Integer appId = Integer.valueOf(args[0]);
		// Set Global AppId
		AppGlobalStatus.setAppId(appId);

		long datetime = DateTimeUtil.datetimeToSecond();

		LoggerSetter.setLogFileName("redstone-" + appId + "-" + datetime);
		LoggerSetter.setLogLevel(LogLevel.DEBUG);

		IndicatorPeriodTimePools.register(IndicatorPeriod.M1, ChinaFuturesSymbol.values());

		StrategyScheduler scheduler = new SPSCStrategyScheduler(2048);

		ParamMap<JctpAdaptorParams> adaptorParam = new ParamMap<>(() -> {

			return null;
		});

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

	}

}
