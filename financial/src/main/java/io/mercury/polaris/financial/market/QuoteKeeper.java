package io.mercury.polaris.financial.market;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.instrument.InstrumentKeeper;
import io.mercury.polaris.financial.market.impl.BasicMarketData;

/**
 * 管理当前最新行情
 * 
 * @creation 2019年4月16日
 */
public final class QuoteKeeper {

	private ImmutableIntObjectMap<AtomicQuote> quoteMap;

	public final static QuoteKeeper Singleton = new QuoteKeeper();

	private QuoteKeeper() {
	}

	public void init() {
		ImmutableList<Instrument> allInstrument = InstrumentKeeper.getAllInstrument();
		MutableIntObjectMap<AtomicQuote> tempQuoteMap = MutableMaps.newIntObjectHashMap();
		if (allInstrument != null)
			allInstrument.forEach(instrument -> tempQuoteMap.put(instrument.id(), new AtomicQuote()));
		quoteMap = tempQuoteMap.toImmutable();
	}

	public void onMarketDate(BasicMarketData marketData) {
		AtomicQuote atomicQuote = quoteMap.get(marketData.getInstrument().id());
		atomicQuote.getAskPrice1().set(marketData.getAskPrice1());
		atomicQuote.getAskVolume1().set(marketData.getAskVolume1());
		atomicQuote.getBidPrice1().set(marketData.getBidPrice1());
		atomicQuote.getBidVolume1().set(marketData.getBidVolume1());
	}

	public AtomicQuote getQuote(Instrument instrument) {
		return quoteMap.get(instrument.id());
	}

	public static class AtomicQuote {

		private AtomicLong askPrice1;
		private AtomicLong askVolume1;
		private AtomicLong bidPrice1;
		private AtomicLong bidVolume1;

		public AtomicQuote() {
			super();
			this.askPrice1 = new AtomicLong();
			this.askVolume1 = new AtomicLong();
			this.bidPrice1 = new AtomicLong();
			this.bidVolume1 = new AtomicLong();
		}

		public AtomicLong getAskPrice1() {
			return askPrice1;
		}

		public AtomicLong getAskVolume1() {
			return askVolume1;
		}

		public AtomicLong getBidPrice1() {
			return bidPrice1;
		}

		public AtomicLong getBidVolume1() {
			return bidVolume1;
		}

	}

}
