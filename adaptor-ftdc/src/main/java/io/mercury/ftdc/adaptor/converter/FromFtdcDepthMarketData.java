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
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.ftdc.gateway.bean.FtdcDepthMarketData;

public final class FromFtdcDepthMarketData implements Function<FtdcDepthMarketData, BasicMarketData> {

	private static final Logger log = CommonLoggerFactory.getLogger(FromFtdcDepthMarketData.class);

	private final DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newDateTimeFormatter();

	private final DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newDateTimeFormatter();

	@Override
	public BasicMarketData apply(FtdcDepthMarketData depthMarketData) {

		LocalDate actionDay = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime updateTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * TimeConst.NANOS_PER_MILLIS);

		Instrument instrument = InstrumentManager.getInstrument(depthMarketData.getInstrumentID());
		log.info("Convert depthMarketData apply -> InstrumentCode==[{}], actionDay==[{}], updateTime==[{}]",
				instrument.code(), actionDay, updateTime);

		PriceMultiplier multiplier = instrument.symbol().getPriceMultiplier();

		return new BasicMarketData(instrument, ZonedDateTime.of(actionDay, updateTime, TimeZone.CST),
				multiplier.toLong(depthMarketData.getLastPrice()), depthMarketData.getVolume(),
				multiplier.toLong(depthMarketData.getTurnover()))

						.setBidPrice1(multiplier.toLong(depthMarketData.getBidPrice1()))
						.setBidVolume1(depthMarketData.getBidVolume1())

						.setBidPrice2(multiplier.toLong(depthMarketData.getBidPrice2()))
						.setBidVolume2(depthMarketData.getBidVolume2())

						.setBidPrice3(multiplier.toLong(depthMarketData.getBidPrice3()))
						.setBidVolume3(depthMarketData.getBidVolume3())

						.setBidPrice4(multiplier.toLong(depthMarketData.getBidPrice4()))
						.setBidVolume4(depthMarketData.getBidVolume4())

						.setBidPrice5(multiplier.toLong(depthMarketData.getBidPrice5()))
						.setBidVolume5(depthMarketData.getBidVolume5())

						.setAskPrice1(multiplier.toLong(depthMarketData.getAskPrice1()))
						.setAskVolume1(depthMarketData.getAskVolume1())

						.setAskPrice2(multiplier.toLong(depthMarketData.getAskPrice2()))
						.setAskVolume2(depthMarketData.getAskVolume2())

						.setAskPrice3(multiplier.toLong(depthMarketData.getAskPrice3()))
						.setAskVolume3(depthMarketData.getAskVolume3())

						.setAskPrice4(multiplier.toLong(depthMarketData.getAskPrice4()))
						.setAskVolume4(depthMarketData.getAskVolume4())

						.setAskPrice5(multiplier.toLong(depthMarketData.getAskPrice5()))
						.setAskVolume5(depthMarketData.getAskVolume5());

	}

}
