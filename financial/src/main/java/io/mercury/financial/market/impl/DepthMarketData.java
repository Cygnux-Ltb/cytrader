package io.mercury.financial.market.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.market.api.QuoteLevelOverflowException;

public final class DepthMarketData implements MarketData {

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

	public DepthMarketData addAskQuote(int level, long price, int volume) throws QuoteLevelOverflowException {
		quotes.addAskQuote(level, price, volume);
		return this;
	}

	public DepthMarketData addBidQuote(int level, long price, int volume) throws QuoteLevelOverflowException {
		quotes.addBidQuote(level, price, volume);
		return this;
	}

	@Override
	public MarketDataType marketDataType() {
		return MarketDataType.Depth;
	}

	public static final class Quotes {

		private AskQuote[] askQuotes;
		private BidQuote[] bidQuotes;

		private int level;

		private Quotes(int level) {
			this.level = level;
			this.askQuotes = new AskQuote[level];
			for (int i = 0; i < askQuotes.length; i++)
				askQuotes[i] = new AskQuote(0, 0);
			this.bidQuotes = new BidQuote[level];
			for (int i = 0; i < bidQuotes.length; i++)
				bidQuotes[i] = new BidQuote(0, 0);
		}

		public static Quotes newLevel5() {
			return new Quotes(5);
		}

		public static Quotes newLevel10() {
			return new Quotes(10);
		}

		public static Quotes newQuotes(int level) {
			return new Quotes(level);
		}

		public AskQuote[] getAskQuotes() {
			return askQuotes;
		}

		public BidQuote[] getBidQuotes() {
			return bidQuotes;
		}

		public int getLevel() {
			return level;
		}

		public Quotes addQuote(int levelIndex, long price, int volume, QuoteType type) {
			switch (type) {
			case Bid:
				return addBidQuote(levelIndex, price, volume);
			case Ask:
				return addAskQuote(levelIndex, price, volume);
			default:
				throw new NoSuchElementException("QuoteType -> (" + type + ") is");
			}
		}

		public Quotes addAskQuote(int levelIndex, long price, int volume) throws QuoteLevelOverflowException {
			if (levelIndex >= level)
				throw new QuoteLevelOverflowException(
						"Get askLevelIndex == " + levelIndex + ", array length is " + level);
			askQuotes[levelIndex].setPrice(price).setVolume(volume);
			return this;
		}

		public Quotes addBidQuote(int levelIndex, long price, int volume) throws QuoteLevelOverflowException {
			if (levelIndex >= level)
				throw new QuoteLevelOverflowException(
						"Get bidLevelIndex == " + levelIndex + ", array length is " + level);
			bidQuotes[levelIndex].setPrice(price).setVolume(volume);
			return this;
		}

	}

	public static enum QuoteType {
		Bid, Ask
	}

	private static abstract class Quote<T extends Quote<T>> implements Comparable<T> {

		protected long price;
		protected int volume;

		protected Quote(long price, int volume) {
			this.price = price;
			this.volume = volume;
		}

		public long getPrice() {
			return price;
		}

		public int getVolume() {
			return volume;
		}

		public abstract T setPrice(long price);

		public abstract T setVolume(int volume);

		public abstract QuoteType getType();
	}

	public static final class AskQuote extends Quote<AskQuote> {

		private AskQuote(long price, int volume) {
			super(price, volume);
		}

		@Override
		public int compareTo(AskQuote o) {
			return getPrice() < o.getPrice() ? -1 : getPrice() > o.getPrice() ? 1 : 0;
		}

		@Override
		public QuoteType getType() {
			return QuoteType.Ask;
		}

		@Override
		public AskQuote setPrice(long price) {
			this.price = price;
			return null;
		}

		@Override
		public AskQuote setVolume(int volume) {
			this.volume = volume;
			return null;
		}

	}

	public static final class BidQuote extends Quote<BidQuote> {

		private BidQuote(long price, int volume) {
			super(price, volume);
		}

		@Override
		public int compareTo(BidQuote o) {
			return getPrice() > o.getPrice() ? -1 : getPrice() < o.getPrice() ? 1 : 0;
		}

		@Override
		public QuoteType getType() {
			return QuoteType.Bid;
		}

		@Override
		public BidQuote setPrice(long price) {
			this.price = price;
			return this;
		}

		@Override
		public BidQuote setVolume(int volume) {
			this.volume = volume;
			return this;
		}

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
