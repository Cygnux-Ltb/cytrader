package io.ffreedom.redstone.core.data;

import static io.ffreedom.financial.futures.ChinaFuturesUtil.TRADING_DAY_DIVIDING_LINE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import io.ffreedom.common.datetime.DateTimeStyle;
import io.ffreedom.financial.futures.ChinaFuturesUtil;

public class FileMarketData implements Comparable<FileMarketData> {

	private String timestamp;
	private String instrumentID;
	private String exchangeID;
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
	private String bidSize1;
	private String bidSize2;
	private String bidSize3;
	private String bidSize4;
	private String bidSize5;
	private String askSize1;
	private String askSize2;
	private String askSize3;
	private String askSize4;
	private String askSize5;
	private String volume;
	private String amount;
	private String openInterest;
	private String updateTime;

	private boolean expired = false;

	public final static String CSV_HEAD = "timestamp,instrumentID,exchangeID,last,iopv,"
			+ "bid1,bid2,bid3,bid4,bid5,ask1,ask2,ask3,ask4,ask5,bidSize1,bidSize2,bidSize3,bidSize4,bidSize5,"
			+ "askSize1,askSize2,askSize3,askSize4,askSize5,volume,amount,openInterest,updateTime";

	public String getTimestamp() {
		return timestamp;
	}

	public String getInstrumentID() {
		return instrumentID;
	}

	public String getExchangeID() {
		return exchangeID;
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

	public String getBidSize1() {
		return bidSize1;
	}

	public String getBidSize2() {
		return bidSize2;
	}

	public String getBidSize3() {
		return bidSize3;
	}

	public String getBidSize4() {
		return bidSize4;
	}

	public String getBidSize5() {
		return bidSize5;
	}

	public String getAskSize1() {
		return askSize1;
	}

	public String getAskSize2() {
		return askSize2;
	}

	public String getAskSize3() {
		return askSize3;
	}

	public String getAskSize4() {
		return askSize4;
	}

	public String getAskSize5() {
		return askSize5;
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
	 * @param bidSize1
	 * @param bidSize2
	 * @param bidSize3
	 * @param bidSize4
	 * @param bidSize5
	 * @param askSize1
	 * @param askSize2
	 * @param askSize3
	 * @param askSize4
	 * @param askSize5
	 * @param volume
	 * @param amount
	 * @param openInterest
	 * @param updateTime
	 */
	private FileMarketData(String timestamp, String instrumentID, String symbol, String exchangeID, String last,
			String iopv, String bid1, String bid2, String bid3, String bid4, String bid5, String ask1, String ask2,
			String ask3, String ask4, String ask5, String bidSize1, String bidSize2, String bidSize3, String bidSize4,
			String bidSize5, String askSize1, String askSize2, String askSize3, String askSize4, String askSize5,
			String volume, String amount, String openInterest, String updateTime) {
		this.timestamp = timestamp;
		this.instrumentID = instrumentID;
		this.exchangeID = exchangeID;
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
		this.bidSize1 = bidSize1;
		this.bidSize2 = bidSize2;
		this.bidSize3 = bidSize3;
		this.bidSize4 = bidSize4;
		this.bidSize5 = bidSize5;
		this.askSize1 = askSize1;
		this.askSize2 = askSize2;
		this.askSize3 = askSize3;
		this.askSize4 = askSize4;
		this.askSize5 = askSize5;
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
		buffer.append(timestamp).append(",").append(instrumentID).append(",").append(exchangeID).append(",")
				.append(last).append(",").append(iopv).append(",").append(bid1).append(",").append(bid2).append(",")
				.append(bid3).append(",").append(bid4).append(",").append(bid5).append(",").append(ask1).append(",")
				.append(ask2).append(",").append(ask3).append(",").append(ask4).append(",").append(ask5).append(",")
				.append(bidSize1).append(",").append(bidSize2).append(",").append(bidSize3).append(",").append(bidSize4)
				.append(",").append(bidSize5).append(",").append(askSize1).append(",").append(askSize2).append(",")
				.append(askSize3).append(",").append(askSize4).append(",").append(askSize5).append(",").append(volume)
				.append(",").append(amount).append(",").append(openInterest).append(",").append(updateTime);
	}

	@Override
	public String toString() {
		return buffer.toString();
	}

	private LocalDate localTimestampDate;
	private LocalTime localTimestampTime;

	private void setLocalDateTime() {
		String[] split = timestamp.split(" ");
		try {
			this.localTimestampDate = LocalDate.parse(split[0], DateTimeStyle.YYYYMMDD.newDateTimeFormatter());
			this.localTimestampTime = LocalTime.parse(split[1], DateTimeStyle.HH_MM_SS_MICROSECOND.newDateTimeFormatter());
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
		this.tradingDay = tradingDayLocalDate.format(DateTimeStyle.YYYYMMDD.newDateTimeFormatter());
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

	public boolean equalsInstrumentId(FileMarketData o) {
		return instrumentID.equals(o.getInstrumentID());
	}

	@Override
	public int compareTo(FileMarketData o) {
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
		this.diskLocation = exchangeID + "/" + symbol + "/" + tradingDay;
	}

	public String getDiskLocation() {
		return diskLocation;
	}

	public static FileMarketData newFileMarketData4CsvLine(String line) {
		String[] strArray = line.split(",");
		String timestamp = strArray[0];
		String instrumentID = strArray[1];
		String symbol = ChinaFuturesUtil.analysisSymbolCode(instrumentID);
		String exchangeID = strArray[2];
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
		String bidSize1 = strArray[15];
		String bidSize2 = strArray[16];
		String bidSize3 = strArray[17];
		String bidSize4 = strArray[18];
		String bidSize5 = strArray[19];
		String askSize1 = strArray[20];
		String askSize2 = strArray[21];
		String askSize3 = strArray[22];
		String askSize4 = strArray[23];
		String askSize5 = strArray[24];
		String volume = strArray[25];
		String amount = strArray[26];
		String openInterest = strArray[27];
		String updateTime = strArray[28];

		return new FileMarketData(timestamp, instrumentID, symbol, exchangeID, last, iopv, bid1, bid2, bid3, bid4, bid5,
				ask1, ask2, ask3, ask4, ask5, bidSize1, bidSize2, bidSize3, bidSize4, bidSize5, askSize1, askSize2,
				askSize3, askSize4, askSize5, volume, amount, openInterest, updateTime);

	}

	public static FileMarketData newFileMarketData(String timestamp, String instrumentID, String symbol,
			String exchangeID, String last, String iopv, String bid1, String bid2, String bid3, String bid4,
			String bid5, String ask1, String ask2, String ask3, String ask4, String ask5, String bidSize1,
			String bidSize2, String bidSize3, String bidSize4, String bidSize5, String askSize1, String askSize2,
			String askSize3, String askSize4, String askSize5, String volume, String amount, String openInterest,
			String updateTime) {
		return new FileMarketData(timestamp, instrumentID, symbol, exchangeID, last, iopv, bid1, bid2, bid3, bid4, bid5,
				ask1, ask2, ask3, ask4, ask5, bidSize1, bidSize2, bidSize3, bidSize4, bidSize5, askSize1, askSize2,
				askSize3, askSize4, askSize5, volume, amount, openInterest, updateTime);
	}

}
