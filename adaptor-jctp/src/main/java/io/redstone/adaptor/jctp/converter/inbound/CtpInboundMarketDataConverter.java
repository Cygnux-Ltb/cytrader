package io.redstone.adaptor.jctp.converter.inbound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;

import io.ffreedom.common.datetime.Pattern.DatePattern;
import io.ffreedom.common.datetime.Pattern.TimePattern;
import io.ffreedom.common.datetime.TimeZones;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.jctp.bean.rsp.RspDepthMarketData;
import io.polaris.financial.Instrument;
import io.polaris.market.impl.BasicMarketData;
import io.redstone.storage.InstrumentKeeper;

public class CtpInboundMarketDataConverter implements Converter<RspDepthMarketData, BasicMarketData> {

	private DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newFormatter();

	private DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newFormatter();

	private Logger logger = CommonLoggerFactory.getLogger(CtpInboundMarketDataConverter.class);

	@Override
	public BasicMarketData convert(RspDepthMarketData depthMarketData) {
		LocalDate depthDate = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime depthTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * 1000_000);
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
