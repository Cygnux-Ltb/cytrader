package io.ffreedom.redstone.adaptor.ctp.converter.inbound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import io.ffreedom.common.datetime.DateTimeStyle;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.polaris.market.MarketData;
import io.ffreedom.polaris.market.QuoteLevelOverflowException;
import io.ffreedom.redstone.actor.InstrumentKeeper;

public class CtpInboundMarketDataConverter implements Converter<CThostFtdcDepthMarketDataField, BasicMarketData> {

	private DateTimeFormatter updateTimeformatter = DateTimeStyle.HH_MM_SS.newFormatter();

	private DateTimeFormatter actionDayformatter = DateTimeStyle.YYYYMMDD.newFormatter();

	private Logger logger = LoggerFactory.getLogger(CtpInboundMarketDataConverter.class);

	@Override
	public BasicMarketData convert(CThostFtdcDepthMarketDataField ctpMarketData) {

		LocalDate date = LocalDate.parse(ctpMarketData.getActionDay(), actionDayformatter);

		LocalTime time = LocalTime.parse(ctpMarketData.getUpdateTime(), updateTimeformatter)
				.plus(ctpMarketData.getUpdateMillisec(), ChronoUnit.MILLIS);

		Instrument instrument = InstrumentKeeper.getInstrument(ctpMarketData.getInstrumentID());

		MarketData marketData = null;
		try {
			marketData = new BasicMarketData(date, time, instrument, 5).setLastPrice(ctpMarketData.getLastPrice())
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
			logger.error(e.getMessage(), e);
		}
		return marketData;
	}

}
