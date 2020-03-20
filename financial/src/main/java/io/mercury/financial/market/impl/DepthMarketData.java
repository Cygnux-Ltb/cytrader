package io.mercury.financial.market.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.QuoteLevelOverflowException;

public final class DepthMarketData {

	private LocalDateTime datetime;
	private Instrument instrument;
	private long lastPrice;
	private long volume;
	private long turnover;
	private Quotes quotes;

	public DepthMarketData(LocalDateTime datetime, Instrument instrument, int quoteLevel) {
		this.datetime = datetime;
		this.instrument = instrument;
		quotes = Quotes.newQuotes(quoteLevel);
	}

	public DepthMarketData(LocalDate date, LocalTime time, Instrument instrument, int quoteLevel) {
		this(LocalDateTime.of(date, time), instrument, quoteLevel);
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public DepthMarketData setLastPrice(long lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

	public double getVolume() {
		return volume;
	}

	public DepthMarketData setVolume(long volume) {
		this.volume = volume;
		return this;
	}

	public double getTurnover() {
		return turnover;
	}

	public DepthMarketData setTurnover(long turnover) {
		this.turnover = turnover;
		return this;
	}

	public Quotes getQuotes() {
		return quotes;
	}

	public DepthMarketData addAskQuote(int level, long price, long volume) throws QuoteLevelOverflowException {
		quotes.addAskQuote(level, price, volume);
		return this;
	}

	public DepthMarketData addBidQuote(int level, long price, long volume) throws QuoteLevelOverflowException {
		quotes.addBidQuote(level, price, volume);
		return this;
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
