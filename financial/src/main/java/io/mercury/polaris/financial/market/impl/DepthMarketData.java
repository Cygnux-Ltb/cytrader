package io.mercury.polaris.financial.market.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.api.QuoteLevelOverflowException;

public final class DepthMarketData {

	private LocalDateTime datetime;
	private Instrument instrument;
	private double lastPrice;
	private double volume;
	private double turnover;
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

	public DepthMarketData setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
		return this;
	}

	public double getVolume() {
		return volume;
	}

	public DepthMarketData setVolume(double volume) {
		this.volume = volume;
		return this;
	}

	public double getTurnover() {
		return turnover;
	}

	public DepthMarketData setTurnover(double turnover) {
		this.turnover = turnover;
		return this;
	}

	public Quotes getQuotes() {
		return quotes;
	}

	public DepthMarketData addAskQuote(int level, double price, double volume) throws QuoteLevelOverflowException {
		quotes.addAskQuote(level, price, volume);
		return this;
	}

	public DepthMarketData addBidQuote(int level, double price, double volume) throws QuoteLevelOverflowException {
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
