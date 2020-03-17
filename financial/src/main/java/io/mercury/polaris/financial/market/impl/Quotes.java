package io.mercury.polaris.financial.market.impl;

import java.util.NoSuchElementException;

import io.mercury.polaris.financial.market.api.QuoteLevelOverflowException;

public final class Quotes {

	private AskQuote[] askQuotes;
	private BidQuote[] bidQuotes;

	private int askLevel;
	private int bidLevel;

	private Quotes(int askLevel, int bidLevel) {
		this.askLevel = askLevel;
		this.bidLevel = bidLevel;
		this.askQuotes = new AskQuote[askLevel];
		for (int i = 0; i < askQuotes.length; i++)
			askQuotes[i] = new AskQuote(0, 0);
		this.bidQuotes = new BidQuote[bidLevel];
		for (int i = 0; i < bidQuotes.length; i++)
			bidQuotes[i] = new BidQuote(0, 0);
	}

	public static Quotes newLevel5() {
		return new Quotes(5, 5);
	}

	public static Quotes newLevel10() {
		return new Quotes(10, 10);
	}

	public static Quotes newQuotes(int level) {
		return new Quotes(level, level);
	}

	public static Quotes newQuotes(int askLevel, int bidLevel) {
		return new Quotes(askLevel, bidLevel);
	}

	public AskQuote[] getAskQuotes() {
		return askQuotes;
	}

	public BidQuote[] getBidQuotes() {
		return bidQuotes;
	}

	public Quotes addQuote(int levelIndex, long price, long volume, QuoteType type) {
		switch (type) {
		case Bid:
			return addBidQuote(levelIndex, price, volume);
		case Ask:
			return addAskQuote(levelIndex, price, volume);
		default:
			throw new NoSuchElementException("QuoteType -> (" + type + ") is");
		}
	}

	public Quotes addAskQuote(int levelIndex, long price, long volume) throws QuoteLevelOverflowException {
		if (levelIndex == askLevel)
			throw new QuoteLevelOverflowException("askLevelIndex == " + levelIndex + ", array length is " + askLevel);
		askQuotes[levelIndex].setPrice(price).setVolume(volume);
		return this;
	}

	public Quotes addBidQuote(int levelindex, long price, long volume) throws QuoteLevelOverflowException {
		if (levelindex == bidLevel)
			throw new QuoteLevelOverflowException("bidLevelIndex == " + levelindex + ", array length is " + bidLevel);
		bidQuotes[levelindex].setPrice(price).setVolume(volume);
		return this;
	}

	public static class AskQuote extends Quote<AskQuote> {

		private AskQuote(long price, long volume) {
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
		public AskQuote setVolume(long volume) {
			this.volume = volume;
			return null;
		}

	}

	public static class BidQuote extends Quote<BidQuote> {

		private BidQuote(long price, long volume) {
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
		public BidQuote setVolume(long volume) {
			this.volume = volume;
			return this;
		}

	}

	public static enum QuoteType {
		Bid, Ask
	}

	private static abstract class Quote<T extends Quote<T>> implements Comparable<T> {

		protected long price;
		protected long volume;

		protected Quote(long price, long volume) {
			this.price = price;
			this.volume = volume;
		}

		public long getPrice() {
			return price;
		}

		public long getVolume() {
			return volume;
		}

		public abstract T setPrice(long price);

		public abstract T setVolume(long volume);

		public abstract QuoteType getType();
	}

}