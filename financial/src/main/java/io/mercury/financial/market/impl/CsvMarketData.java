package io.mercury.financial.market.impl;

import static io.mercury.financial.instrument.futures.misc.ChinaFuturesSupporter.TRADING_DAY_DIVIDING_LINE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.PatternSymbol;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.financial.instrument.futures.misc.ChinaFuturesSupporter;

public class CsvMarketData implements Comparable<CsvMarketData> {

	private String timestamp;
	private String instrumentId;
	private String exchangeId;
	private String last;
	private String iopv;
	private String bid1;
	private String bid2;
	private String bid3;
	private String bid4;
	private String bid5;
	private String ask1;
	private String ask2;
	private String ask3;
	private String ask4;
	private String ask5;
	private String bidVolume1;
	private String bidVolume2;
	private String bidVolume3;
	private String bidVolume4;
	private String bidVolume5;
	private String askVolume1;
	private String askVolume2;
	private String askVolume3;
	private String askVolume4;
	private String askVolume5;
	private String volume;
	private String amount;
	private String openInterest;
	private String updateTime;

	private boolean expired = false;

	public final static String CSV_HEAD = "timestamp,instrumentId,exchangeId,last,iopv,"
			+ "bid1,bid2,bid3,bid4,bid5,ask1,ask2,ask3,ask4,ask5,bidVolume1,bidVolume2,bidVolume3,bidVolume4,bidVolume5,"
			+ "askVolume1,askVolume2,askVolume3,askVolume4,askVolume5,volume,amount,openInterest,updateTime";

	public String getTimestamp() {
		return timestamp;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public String getLast() {
		return last;
	}

	public String getIopv() {
		return iopv;
	}

	public String getBid1() {
		return bid1;
	}

	public String getBid2() {
		return bid2;
	}

	public String getBid3() {
		return bid3;
	}

	public String getBid4() {
		return bid4;
	}

	public String getBid5() {
		return bid5;
	}

	public String getAsk1() {
		return ask1;
	}

	public String getAsk2() {
		return ask2;
	}

	public String getAsk3() {
		return ask3;
	}

	public String getAsk4() {
		return ask4;
	}

	public String getAsk5() {
		return ask5;
	}

	public String getBidVolume1() {
		return bidVolume1;
	}

	public String getBidVolume2() {
		return bidVolume2;
	}

	public String getBidVolume3() {
		return bidVolume3;
	}

	public String getBidVolume4() {
		return bidVolume4;
	}

	public String getBidVolume5() {
		return bidVolume5;
	}

	public String getAskVolume1() {
		return askVolume1;
	}

	public String getAskVolume2() {
		return askVolume2;
	}

	public String getAskVolume3() {
		return askVolume3;
	}

	public String getAskVolume4() {
		return askVolume4;
	}

	public String getAskVolume5() {
		return askVolume5;
	}

	public String getVolume() {
		return volume;
	}

	public String getAmount() {
		return amount;
	}

	public String getOpenInterest() {
		return openInterest;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param timestamp
	 * @param instrumentID
	 * @param exchangeID
	 * @param last
	 * @param iopv
	 * @param bid1
	 * @param bid2
	 * @param bid3
	 * @param bid4
	 * @param bid5
	 * @param ask1
	 * @param ask2
	 * @param ask3
	 * @param ask4
	 * @param ask5
	 * @param bidVolume1
	 * @param bidVolume2
	 * @param bidVolume3
	 * @param bidVolume4
	 * @param bidVolume5
	 * @param askVolume1
	 * @param askVolume2
	 * @param askVolume3
	 * @param askVolume4
	 * @param askVolume5
	 * @param volume
	 * @param amount
	 * @param openInterest
	 * @param updateTime
	 */
	private CsvMarketData(String timestamp, String instrumentId, String symbol, String exchangeId, String last,
			String iopv, String bid1, String bid2, String bid3, String bid4, String bid5, String ask1, String ask2,
			String ask3, String ask4, String ask5, String bidVolume1, String bidVolume2, String bidVolume3,
			String bidVolume4, String bidVolume5, String askVolume1, String askVolume2, String askVolume3,
			String askVolume4, String askVolume5, String volume, String amount, String openInterest,
			String updateTime) {
		this.timestamp = timestamp;
		this.instrumentId = instrumentId;
		this.exchangeId = exchangeId;
		this.symbol = symbol;
		this.last = last;
		this.iopv = iopv;
		this.bid1 = bid1;
		this.bid2 = bid2;
		this.bid3 = bid3;
		this.bid4 = bid4;
		this.bid5 = bid5;
		this.ask1 = ask1;
		this.ask2 = ask2;
		this.ask3 = ask3;
		this.ask4 = ask4;
		this.ask5 = ask5;
		this.bidVolume1 = bidVolume1;
		this.bidVolume2 = bidVolume2;
		this.bidVolume3 = bidVolume3;
		this.bidVolume4 = bidVolume4;
		this.bidVolume5 = bidVolume5;
		this.askVolume1 = askVolume1;
		this.askVolume2 = askVolume2;
		this.askVolume3 = askVolume3;
		this.askVolume4 = askVolume4;
		this.askVolume5 = askVolume5;
		this.volume = volume;
		this.amount = amount;
		this.openInterest = openInterest;
		this.updateTime = updateTime;
		init();
	}

	private void init() {
		initStringBuffer();
		try {
			setLocalDateTime();
			analysisTradingDay();
			findDiskLocation();
		} catch (Exception e) {
			this.expired = true;
		}
	}

	public boolean isExpired() {
		return expired;
	}

	public static String getCsvHead() {
		return CSV_HEAD;
	}

	private StringBuffer buffer;

	private void initStringBuffer() {
		buffer = new StringBuffer();
		buffer.append(timestamp).append(",").append(instrumentId).append(",").append(exchangeId).append(",")
				.append(last).append(",").append(iopv).append(",").append(bid1).append(",").append(bid2).append(",")
				.append(bid3).append(",").append(bid4).append(",").append(bid5).append(",").append(ask1).append(",")
				.append(ask2).append(",").append(ask3).append(",").append(ask4).append(",").append(ask5).append(",")
				.append(bidVolume1).append(",").append(bidVolume2).append(",").append(bidVolume3).append(",")
				.append(bidVolume4).append(",").append(bidVolume5).append(",").append(askVolume1).append(",")
				.append(askVolume2).append(",").append(askVolume3).append(",").append(askVolume4).append(",")
				.append(askVolume5).append(",").append(volume).append(",").append(amount).append(",")
				.append(openInterest).append(",").append(updateTime);
	}

	@Override
	public String toString() {
		return buffer.toString();
	}

	private LocalDate localTimestampDate;
	private LocalTime localTimestampTime;

	private void setLocalDateTime() {
		String[] split = timestamp.split(PatternSymbol.BLANK);
		try {
			this.localTimestampDate = LocalDate.parse(split[0], DatePattern.YYYYMMDD.getFormatter());
			this.localTimestampTime = LocalTime.parse(split[1], TimePattern.HH_MM_SS_SSSSSS.getFormatter());
			setLocalTimestamp();
		} catch (Exception e) {
			// System.out.println(JSON.toJSONString(this));
		}
	}

	private LocalDateTime localTimestamp;

	private void setLocalTimestamp() {
		this.localTimestamp = LocalDateTime.of(localTimestampDate, localTimestampTime);
	}

	public LocalDateTime getLocalTimestamp() {
		return localTimestamp;
	}

	/**
	 * Symbol
	 */
	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	/**
	 * TradingDay
	 */
	private String tradingDay;

	public String getTradingDay() {
		return tradingDay;
	}

	private void analysisTradingDay() {
		LocalDate tempLocalDate = LocalDate.from(localTimestampDate);
		LocalTime tempLocalTime = LocalTime.from(localTimestampTime);
		DayOfWeek dayOfWeek = tempLocalDate.getDayOfWeek();
		LocalDate tradingDayLocalDate;
		// 判断是否是夜盘
		if (isNightTrading(tempLocalTime)) {
			// 判断是否周五,是加3天,否则加1天.
			if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				tradingDayLocalDate = tempLocalDate.plusDays(3);
			} else {
				tradingDayLocalDate = tempLocalDate.plusDays(1);
			}
		} else {
			// 判断是否周六,是加2天,否则不加.
			if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
				tradingDayLocalDate = tempLocalDate.plusDays(2);
			} else {
				tradingDayLocalDate = tempLocalDate;
			}
		}
		this.tradingDay = tradingDayLocalDate.format(DatePattern.YYYYMMDD.getFormatter());
	}

	private boolean isNightTrading(LocalTime time) {
		if (time.isAfter(TRADING_DAY_DIVIDING_LINE)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

	}

	public boolean equalsInstrumentId(CsvMarketData o) {
		return instrumentId.equals(o.getInstrumentId());
	}

	@Override
	public int compareTo(CsvMarketData o) {
		if (equalsInstrumentId(o)) {
			if (this.localTimestamp.isEqual(o.getLocalTimestamp())) {
				return 0;
			}
		}
		if (this.localTimestamp.isAfter(o.getLocalTimestamp())) {
			return 1;
		} else {
			return -1;
		}

	}

	private String diskLocation;

	private void findDiskLocation() {
		this.diskLocation = exchangeId + "/" + symbol + "/" + tradingDay;
	}

	public String getDiskLocation() {
		return diskLocation;
	}

	public static CsvMarketData newFileMarketData4CsvLine(String line) {
		String[] strArray = line.split(",");
		String timestamp = strArray[0];
		String instrumentId = strArray[1];
		String symbol = ChinaFuturesSupporter.analyzeSymbolCode(instrumentId);
		String exchangeId = strArray[2];
		String last = strArray[3];
		String iopv = strArray[4];
		String bid1 = strArray[5];
		String bid2 = strArray[6];
		String bid3 = strArray[7];
		String bid4 = strArray[8];
		String bid5 = strArray[9];
		String ask1 = strArray[10];
		String ask2 = strArray[11];
		String ask3 = strArray[12];
		String ask4 = strArray[13];
		String ask5 = strArray[14];
		String bidVolume1 = strArray[15];
		String bidVolume2 = strArray[16];
		String bidVolume3 = strArray[17];
		String bidVolume4 = strArray[18];
		String bidVolume5 = strArray[19];
		String askVolume1 = strArray[20];
		String askVolume2 = strArray[21];
		String askVolume3 = strArray[22];
		String askVolume4 = strArray[23];
		String askVolume5 = strArray[24];
		String volume = strArray[25];
		String amount = strArray[26];
		String openInterest = strArray[27];
		String updateTime = strArray[28];
		return new CsvMarketData(timestamp, instrumentId, symbol, exchangeId, last, iopv, bid1, bid2, bid3, bid4, bid5,
				ask1, ask2, ask3, ask4, ask5, bidVolume1, bidVolume2, bidVolume3, bidVolume4, bidVolume5, askVolume1,
				askVolume2, askVolume3, askVolume4, askVolume5, volume, amount, openInterest, updateTime);
	}

	public static CsvMarketData newFileMarketData(String timestamp, String instrumentID, String symbol,
			String exchangeID, String last, String iopv, String bid1, String bid2, String bid3, String bid4,
			String bid5, String ask1, String ask2, String ask3, String ask4, String ask5, String bidVolume1,
			String bidVolume2, String bidVolume3, String bidVolume4, String bidVolume5, String askVolume1,
			String askVolume2, String askVolume3, String askVolume4, String askVolume5, String volume, String amount,
			String openInterest, String updateTime) {
		return new CsvMarketData(timestamp, instrumentID, symbol, exchangeID, last, iopv, bid1, bid2, bid3, bid4, bid5,
				ask1, ask2, ask3, ask4, ask5, bidVolume1, bidVolume2, bidVolume3, bidVolume4, bidVolume5, askVolume1,
				askVolume2, askVolume3, askVolume4, askVolume5, volume, amount, openInterest, updateTime);
	}

}
