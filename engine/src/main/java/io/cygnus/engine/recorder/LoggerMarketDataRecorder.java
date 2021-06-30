package io.cygnus.engine.recorder;

import java.io.IOException;

import org.slf4j.Logger;

import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.event.MarketDataRecorder.AbstractMarketDataRecorder;
import io.mercury.common.log.CommonLoggerFactory;

public final class LoggerMarketDataRecorder extends AbstractMarketDataRecorder<BasicMarketData> {

	private static final Logger log = CommonLoggerFactory.getLogger(LoggerMarketDataRecorder.class);

	public LoggerMarketDataRecorder(Instrument... instruments) {
		super(instruments);
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		log.info("MarketData Recorde -> {}", marketData);
	}

	@Override
	public void close() throws IOException {
		log.info("LoggerMarketDataRecorder is closed");
	}

}
