package io.ffreedom.redstone.launch;

import io.ffreedom.common.charset.SysCharacter;
import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.log.LogLevel;
import io.ffreedom.common.log.LoggerSetter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.financial.futures.ChinaFuturesSymbol;
import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.indicators.pools.IndicatorPeriodTimePools;
import io.ffreedom.redstone.actor.ApplicationState;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.adaptor.ctp.CtpInboundAdaptor;
import io.ffreedom.redstone.core.adaptor.InboundAdaptor;
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
		Integer applicationId = Integer.valueOf(args[0]);
		if (applicationId < ApplicationState.MINIMUM_ID) {
			throw new RuntimeException("ApplicationId too small, Not less than -> " + ApplicationState.MINIMUM_ID);
		}
		if (applicationId > ApplicationState.MAXIMUM_ID) {
			throw new RuntimeException("ApplicationId too big, Not greater than -> " + ApplicationState.MAXIMUM_ID);
		}

		// Set Global ApplicationId
		ApplicationState.setApplicationId(applicationId);

		long datetime = DateTimeUtil.datetimeToSecond();

		LoggerSetter.setLogFileName("redstone-" + applicationId + "-" + datetime);
		LoggerSetter.setLogLevel(LogLevel.DEBUG);

		IndicatorPeriodTimePools.INSTANCE.register(IndicatorPeriod.M1, ChinaFuturesSymbol.values());

		StrategyScheduler scheduler = new SPSCStrategyScheduler(2048);

		ParamMap<AdaptorParams> adaptorParam = new ParamMap<>(() -> {

			return null;
		});

		InboundAdaptor inboundAdaptor = new CtpInboundAdaptor(scheduler, adaptorParam);

		inboundAdaptor.activate();

	}

}
