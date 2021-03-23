package io.cygnus.example.launch;

import java.time.Duration;
import java.util.Properties;

import org.slf4j.Logger;

import io.cygnus.engine.scheduler.SyncSingleStrategyScheduler;
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
import io.mercury.common.log.CommonLogConfigurator;
import io.mercury.common.log.CommonLogConfigurator.LogLevel;
import io.mercury.common.param.ImmutableParams;

public final class ExampleWithProperties {

	public static void main(String[] args) {
		
		
		
		
		
	}

}
