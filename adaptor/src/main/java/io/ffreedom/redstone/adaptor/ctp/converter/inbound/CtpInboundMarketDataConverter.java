package io.ffreedom.redstone.adaptor.ctp.converter.inbound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;

import io.ffreedom.common.datetime.DatePattern;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.financial.Instrument;
import io.ffreedom.market.MarketData;
import io.ffreedom.market.QuoteLevelOverflowException;
import io.ffreedom.redstone.actor.InstrumentState;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundMarketData;

public class CtpInboundMarketDataConverter implements Converter<CtpInboundMarketData, MarketData> {

	private DateTimeFormatter updateTimeformatter = DateTimeFormatter.ofPattern(DatePattern.HH_MM_SS);

	private DateTimeFormatter actionDayformatter = DateTimeFormatter.ofPattern(DatePattern.YYYYMMDD);

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public MarketData convert(CtpInboundMarketData ctpMarketData) {

		LocalDate date = LocalDate.parse(ctpMarketData.getActionDay(), actionDayformatter);

		LocalTime time = LocalTime.parse(ctpMarketData.getUpdateTime(), updateTimeformatter)
				.plus(ctpMarketData.getUpdateMillisec(), ChronoUnit.MILLIS);

		Instrument instrument = InstrumentState.getInstrument(ctpMarketData.getInstrumentID());

		MarketData marketData = null;
		try {
			marketData = new MarketData(date, time, instrument, 5).setLastPrice(ctpMarketData.getLastPrice())
					.setVolume(ctpMarketData.getVolume()).setTurnover(ctpMarketData.getTurnover())
					// Set Bid Price
					.addBidQuote(ctpMarketData.getBidPrice1(), ctpMarketData.getBidVolume1())
					.addBidQuote(ctpMarketData.getBidPrice2(), ctpMarketData.getBidVolume2())
					.addBidQuote(ctpMarketData.getBidPrice3(), ctpMarketData.getBidVolume3())
					.addBidQuote(ctpMarketData.getBidPrice4(), ctpMarketData.getBidVolume4())
					.addBidQuote(ctpMarketData.getBidPrice5(), ctpMarketData.getBidVolume5())
					// Set Ask Price
					.addAskQuote(ctpMarketData.getAskPrice1(), ctpMarketData.getAskVolume1())
					.addAskQuote(ctpMarketData.getAskPrice2(), ctpMarketData.getAskVolume2())
					.addAskQuote(ctpMarketData.getAskPrice3(), ctpMarketData.getAskVolume3())
					.addAskQuote(ctpMarketData.getAskPrice4(), ctpMarketData.getAskVolume4())
					.addAskQuote(ctpMarketData.getAskPrice5(), ctpMarketData.getAskVolume5());
		} catch (QuoteLevelOverflowException e) {
			log.error(e.getMessage(), e);
		}
		return marketData;
	}

}
