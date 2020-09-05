package io.mercury.financial.market.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import io.mercury.common.datetime.EpochTime;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.serialization.json.JsonUtil;

/**
 * @author yellow013
 * @creation 2019年5月24日
 * @description 价格转换使用对应
 */
public class BasicMarketData implements MarketData {

	private Instrument instrument;
	private LocalDateTime datetime;
	private ZonedDateTime zonedDatetime;
	private long epochMillis;
	private long lastPrice;
	private int volume;
	private long turnover;

	/** bid level 10 **/
	private long bidPrice1;
	private int bidVolume1;
	private long bidPrice2;
	private int bidVolume2;
	private long bidPrice3;
	private int bidVolume3;
	private long bidPrice4;
	private int bidVolume4;
	private long bidPrice5;
	private int bidVolume5;
	private long bidPrice6;
	private int bidVolume6;
	private long bidPrice7;
	private int bidVolume7;
	private long bidPrice8;
	private int bidVolume8;
	private long bidPrice9;
	private int bidVolume9;
	private long bidPrice10;
	private int bidVolume10;

	/** ask level 10 **/
	private long askPrice1;
	private int askVolume1;
	private long askPrice2;
	private int askVolume2;
	private long askPrice3;
	private int askVolume3;
	private long askPrice4;
	private int askVolume4;
	private long askPrice5;
	private int askVolume5;
	private long askPrice6;
	private int askVolume6;
	private long askPrice7;
	private int askVolume7;
	private long askPrice8;
	private int askVolume8;
	private long askPrice9;
	private int askVolume9;
	private long askPrice10;
	private int askVolume10;

	public BasicMarketData(Instrument instrument, long epochMillis) {
		this.instrument = instrument;
		this.epochMillis = epochMillis;
	}

	public BasicMarketData(Instrument instrument, LocalDateTime datetime) {
		this.instrument = instrument;
		this.epochMillis = EpochTime.millis(datetime);
		this.datetime = datetime;
	}

	public BasicMarketData(Instrument instrument, ZonedDateTime zonedDatetime) {
		this.instrument = instrument;
		this.epochMillis = EpochTime.millis(zonedDatetime);
		this.zonedDatetime = zonedDatetime;
	}

	public BasicMarketData(Instrument instrument, long epochMillis, long lastPrice, int volume, long turnover) {
		this(instrument, epochMillis, null, null, lastPrice, volume, turnover);
	}

	public BasicMarketData(Instrument instrument, LocalDateTime dateTime, long lastPrice, int volume, long turnover) {
		this(instrument, EpochTime.millis(dateTime), dateTime, null, lastPrice, volume, turnover);
	}

	public BasicMarketData(Instrument instrument, ZonedDateTime zonedDateTime, long lastPrice, int volume,
			long turnover) {
		this(instrument, EpochTime.millis(zonedDateTime), null, zonedDateTime, lastPrice, volume, turnover);
	}

	public BasicMarketData(Instrument instrument, long epochMillis, LocalDateTime datetime, ZonedDateTime zonedDatetime,
			long lastPrice, int volume, long turnover) {
		this.instrument = instrument;
		this.epochMillis = epochMillis;
		this.datetime = datetime;
		this.zonedDatetime = zonedDatetime;
		this.lastPrice = lastPrice;
		this.volume = volume;
		this.turnover = turnover;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	@Override
	public String getInstrumentId() {
		return instrument.code();
	}

	@Override
	public long getEpochMillis() {
		return epochMillis;
	}

	public LocalDateTime datetime() {
		if (datetime == null)
			datetime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis),
					instrument.symbol().exchange().zoneId());
		return datetime;
	}

	public ZonedDateTime zonedDatetime() {
		if (zonedDatetime == null)
			zonedDatetime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMillis),
					instrument.symbol().exchange().zoneId());
		return zonedDatetime;
	}

	@Override
	public long getLastPrice() {
		return lastPrice;
	}

	@Override
	public int getVolume() {
		return volume;
	}

	@Override
	public long getTurnover() {
		return turnover;
	}

	@Override
	public long getBidPrice1() {
		return bidPrice1;
	}

	@Override
	public int getBidVolume1() {
		return bidVolume1;
	}

	public long getBidPrice2() {
		return bidPrice2;
	}

	public int getBidVolume2() {
		return bidVolume2;
	}

	public long getBidPrice3() {
		return bidPrice3;
	}

	public int getBidVolume3() {
		return bidVolume3;
	}

	public long getBidPrice4() {
		return bidPrice4;
	}

	public int getBidVolume4() {
		return bidVolume4;
	}

	public long getBidPrice5() {
		return bidPrice5;
	}

	public int getBidVolume5() {
		return bidVolume5;
	}

	public long getBidPrice6() {
		return bidPrice6;
	}

	public int getBidVolume6() {
		return bidVolume6;
	}

	public long getBidPrice7() {
		return bidPrice7;
	}

	public int getBidVolume7() {
		return bidVolume7;
	}

	public long getBidPrice8() {
		return bidPrice8;
	}

	public int getBidVolume8() {
		return bidVolume8;
	}

	public long getBidPrice9() {
		return bidPrice9;
	}

	public int getBidVolume9() {
		return bidVolume9;
	}

	public long getBidPrice10() {
		return bidPrice10;
	}

	public int getBidVolume10() {
		return bidVolume10;
	}

	@Override
	public long getAskPrice1() {
		return askPrice1;
	}

	@Override
	public int getAskVolume1() {
		return askVolume1;
	}

	public long getAskPrice2() {
		return askPrice2;
	}

	public int getAskVolume2() {
		return askVolume2;
	}

	public long getAskPrice3() {
		return askPrice3;
	}

	public int getAskVolume3() {
		return askVolume3;
	}

	public long getAskPrice4() {
		return askPrice4;
	}

	public int getAskVolume4() {
		return askVolume4;
	}

	public long getAskPrice5() {
		return askPrice5;
	}

	public int getAskVolume5() {
		return askVolume5;
	}

	public long getAskPrice6() {
		return askPrice6;
	}

	public int getAskVolume6() {
		return askVolume6;
	}

	public long getAskPrice7() {
		return askPrice7;
	}

	public int getAskVolume7() {
		return askVolume7;
	}

	public long getAskPrice8() {
		return askPrice8;
	}

	public int getAskVolume8() {
		return askVolume8;
	}

	public long getAskPrice9() {
		return askPrice9;
	}

	public int getAskVolume9() {
		return askVolume9;
	}

	public long getAskPrice10() {
		return askPrice10;
	}

	public int getAskVolume10() {
		return askVolume10;
	}

	public BasicMarketData setLastPrice(long lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

	public BasicMarketData setVolume(int volume) {
		this.volume = volume;
		return this;
	}

	public BasicMarketData setTurnover(long turnover) {
		this.turnover = turnover;
		return this;
	}

	public BasicMarketData setBidPrice1(long bidPrice1) {
		this.bidPrice1 = bidPrice1;
		return this;
	}

	public BasicMarketData setBidVolume1(int bidVolume1) {
		this.bidVolume1 = bidVolume1;
		return this;
	}

	public BasicMarketData setBidPrice2(long bidPrice2) {
		this.bidPrice2 = bidPrice2;
		return this;
	}

	public BasicMarketData setBidVolume2(int bidVolume2) {
		this.bidVolume2 = bidVolume2;
		return this;
	}

	public BasicMarketData setBidPrice3(long bidPrice3) {
		this.bidPrice3 = bidPrice3;
		return this;
	}

	public BasicMarketData setBidVolume3(int bidVolume3) {
		this.bidVolume3 = bidVolume3;
		return this;
	}

	public BasicMarketData setBidPrice4(long bidPrice4) {
		this.bidPrice4 = bidPrice4;
		return this;
	}

	public BasicMarketData setBidVolume4(int bidVolume4) {
		this.bidVolume4 = bidVolume4;
		return this;
	}

	public BasicMarketData setBidPrice5(long bidPrice5) {
		this.bidPrice5 = bidPrice5;
		return this;
	}

	public BasicMarketData setBidVolume5(int bidVolume5) {
		this.bidVolume5 = bidVolume5;
		return this;
	}

	public BasicMarketData setBidPrice6(long bidPrice6) {
		this.bidPrice6 = bidPrice6;
		return this;
	}

	public BasicMarketData setBidVolume6(int bidVolume6) {
		this.bidVolume6 = bidVolume6;
		return this;
	}

	public BasicMarketData setBidPrice7(long bidPrice7) {
		this.bidPrice7 = bidPrice7;
		return this;
	}

	public BasicMarketData setBidVolume7(int bidVolume7) {
		this.bidVolume7 = bidVolume7;
		return this;
	}

	public BasicMarketData setBidPrice8(long bidPrice8) {
		this.bidPrice8 = bidPrice8;
		return this;
	}

	public BasicMarketData setBidVolume8(int bidVolume8) {
		this.bidVolume8 = bidVolume8;
		return this;
	}

	public BasicMarketData setBidPrice9(long bidPrice9) {
		this.bidPrice9 = bidPrice9;
		return this;
	}

	public BasicMarketData setBidVolume9(int bidVolume9) {
		this.bidVolume9 = bidVolume9;
		return this;
	}

	public BasicMarketData setBidPrice10(long bidPrice10) {
		this.bidPrice10 = bidPrice10;
		return this;
	}

	public BasicMarketData setBidVolume10(int bidVolume10) {
		this.bidVolume10 = bidVolume10;
		return this;
	}

	public BasicMarketData setAskPrice1(long askPrice1) {
		this.askPrice1 = askPrice1;
		return this;
	}

	public BasicMarketData setAskVolume1(int askVolume1) {
		this.askVolume1 = askVolume1;
		return this;
	}

	public BasicMarketData setAskPrice2(long askPrice2) {
		this.askPrice2 = askPrice2;
		return this;
	}

	public BasicMarketData setAskVolume2(int askVolume2) {
		this.askVolume2 = askVolume2;
		return this;
	}

	public BasicMarketData setAskPrice3(long askPrice3) {
		this.askPrice3 = askPrice3;
		return this;
	}

	public BasicMarketData setAskVolume3(int askVolume3) {
		this.askVolume3 = askVolume3;
		return this;
	}

	public BasicMarketData setAskPrice4(long askPrice4) {
		this.askPrice4 = askPrice4;
		return this;
	}

	public BasicMarketData setAskVolume4(int askVolume4) {
		this.askVolume4 = askVolume4;
		return this;
	}

	public BasicMarketData setAskPrice5(long askPrice5) {
		this.askPrice5 = askPrice5;
		return this;
	}

	public BasicMarketData setAskVolume5(int askVolume5) {
		this.askVolume5 = askVolume5;
		return this;
	}

	public BasicMarketData setAskPrice6(long askPrice6) {
		this.askPrice6 = askPrice6;
		return this;
	}

	public BasicMarketData setAskVolume6(int askVolume6) {
		this.askVolume6 = askVolume6;
		return this;
	}

	public BasicMarketData setAskPrice7(long askPrice7) {
		this.askPrice7 = askPrice7;
		return this;
	}

	public BasicMarketData setAskVolume7(int askVolume7) {
		this.askVolume7 = askVolume7;
		return this;
	}

	public BasicMarketData setAskPrice8(long askPrice8) {
		this.askPrice8 = askPrice8;
		return this;
	}

	public BasicMarketData setAskVolume8(int askVolume8) {
		this.askVolume8 = askVolume8;
		return this;
	}

	public BasicMarketData setAskPrice9(long askPrice9) {
		this.askPrice9 = askPrice9;
		return this;
	}

	public BasicMarketData setAskVolume9(int askVolume9) {
		this.askVolume9 = askVolume9;
		return this;
	}

	public BasicMarketData setAskPrice10(long askPrice10) {
		this.askPrice10 = askPrice10;
		return this;
	}

	public BasicMarketData setAskVolume10(int askVolume10) {
		this.askVolume10 = askVolume10;
		return this;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonHasNulls(this);
	}

	public static void main(String[] args) {

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalTime plus = time.plus(500, ChronoUnit.MILLIS);
		LocalDateTime dateTime = LocalDateTime.now();
		String format = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS"));
		System.out.println(date);
		System.out.println(plus);
		System.out.println(dateTime);
		System.out.println(format);

	}

}
