package io.mercury.ftdc.adaptor.converter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.slf4j.Logger;

import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZone;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentKeeper;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.ftdc.gateway.bean.FtdcDepthMarketData;

public final class FtdcDepthMarketDataConverter implements Function<FtdcDepthMarketData, BasicMarketData> {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	private final DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newFormatter();

	private final DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newFormatter();

	@Override
	public BasicMarketData apply(FtdcDepthMarketData depthMarketData) {
		LocalDate actionDay = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime updateTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * TimeConst.NANOS_PER_MILLIS);

		Instrument instrument = InstrumentKeeper.getInstrument(depthMarketData.getInstrumentID());
		log.info("Convert depthMarketData apply -> InstrumentCode==[{}], actionDay==[{}], updateTime==[{}]",
				instrument.code(), actionDay, updateTime);

		PriceMultiplier multiplier = instrument.symbol().priceMultiplier();

		return new BasicMarketData(instrument, ZonedDateTime.of(actionDay, updateTime, TimeZone.CST),
				multiplier.convertToLong(depthMarketData.getLastPrice()), depthMarketData.getVolume(),
				multiplier.convertToLong(depthMarketData.getTurnover()))

						.setBidPrice1(multiplier.convertToLong(depthMarketData.getBidPrice1()))
						.setBidVolume1(depthMarketData.getBidVolume1())

						.setBidPrice2(multiplier.convertToLong(depthMarketData.getBidPrice2()))
						.setBidVolume2(depthMarketData.getBidVolume2())

						.setBidPrice3(multiplier.convertToLong(depthMarketData.getBidPrice3()))
						.setBidVolume3(depthMarketData.getBidVolume3())

						.setBidPrice4(multiplier.convertToLong(depthMarketData.getBidPrice4()))
						.setBidVolume4(depthMarketData.getBidVolume4())

						.setBidPrice5(multiplier.convertToLong(depthMarketData.getBidPrice5()))
						.setBidVolume5(depthMarketData.getBidVolume5())

						.setAskPrice1(multiplier.convertToLong(depthMarketData.getAskPrice1()))
						.setAskVolume1(depthMarketData.getAskVolume1())

						.setAskPrice2(multiplier.convertToLong(depthMarketData.getAskPrice2()))
						.setAskVolume2(depthMarketData.getAskVolume2())

						.setAskPrice3(multiplier.convertToLong(depthMarketData.getAskPrice3()))
						.setAskVolume3(depthMarketData.getAskVolume3())

						.setAskPrice4(multiplier.convertToLong(depthMarketData.getAskPrice4()))
						.setAskVolume4(depthMarketData.getAskVolume4())

						.setAskPrice5(multiplier.convertToLong(depthMarketData.getAskPrice5()))
						.setAskVolume5(depthMarketData.getAskVolume5());

	}

}
