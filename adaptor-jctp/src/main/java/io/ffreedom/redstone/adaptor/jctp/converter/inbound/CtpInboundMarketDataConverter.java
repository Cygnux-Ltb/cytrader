package io.ffreedom.redstone.adaptor.jctp.converter.inbound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import io.ffreedom.common.datetime.DateTimeStyle;
import io.ffreedom.common.datetime.TimeZones;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.actor.InstrumentKeeper;

public class CtpInboundMarketDataConverter implements Converter<CThostFtdcDepthMarketDataField, BasicMarketData> {

	private DateTimeFormatter updateTimeformatter = DateTimeStyle.HH_MM_SS.newFormatter();

	private DateTimeFormatter actionDayformatter = DateTimeStyle.YYYYMMDD.newFormatter();

	private Logger logger = CommonLoggerFactory.getLogger(CtpInboundMarketDataConverter.class);

	@Override
	public BasicMarketData convert(CThostFtdcDepthMarketDataField depthMarketData) {
		LocalDate depthDate = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime depthTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plus(depthMarketData.getUpdateMillisec(), ChronoUnit.MILLIS);
		Instrument instrument = InstrumentKeeper.getInstrument(depthMarketData.getInstrumentID());
		logger.info("Convert depthMarketData -> InstrumentCode==[{}], depthDate==[{}], depthTime==[{}]",
				instrument.getInstrumentCode(), depthDate, depthTime);
		return BasicMarketData.of(instrument, ZonedDateTime.of(depthDate, depthTime, TimeZones.CST))
				.setLastPrice(depthMarketData.getLastPrice()).setVolume(depthMarketData.getVolume())
				.setTurnover(depthMarketData.getTurnover()).setBidPrice1(depthMarketData.getBidPrice1())
				.setBidVolume1(depthMarketData.getBidVolume1()).setAskPrice1(depthMarketData.getAskPrice1())
				.setAskVolume1(depthMarketData.getAskVolume1());
	}

}
