package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import com.google.common.util.concurrent.AtomicDouble;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.impl.BasicMarketData;
import io.ffreedom.redstone.storage.InstrumentKeeper;

/**
 * 管理当前最新行情
 * 
 * @creation 2019年4月16日
 */
public class QuoteActor {

	private ImmutableIntObjectMap<AtomicQuote> quoteMap;

	public final static QuoteActor Singleton = new QuoteActor();

	private QuoteActor() {

	}

	public void init() {
		ImmutableList<Instrument> allInstrument = InstrumentKeeper.getAllInstrument();
		MutableIntObjectMap<AtomicQuote> tempQuoteMap = MutableMaps.newIntObjectHashMap();
		if (allInstrument != null)
			allInstrument.forEach(instrument -> tempQuoteMap.put(instrument.getInstrumentId(), new AtomicQuote()));
		quoteMap = tempQuoteMap.toImmutable();
	}

	public void onMarketDate(BasicMarketData marketData) {
		AtomicQuote atomicQuote = quoteMap.get(marketData.getInstrument().getInstrumentId());
		atomicQuote.getAskPrice1().set(marketData.getAskPrice1());
		atomicQuote.getAskVolume1().set(marketData.getAskVolume1());
		atomicQuote.getBidPrice1().set(marketData.getBidPrice1());
		atomicQuote.getBidVolume1().set(marketData.getBidVolume1());
	}

	public AtomicQuote getQuote(Instrument instrument) {
		return quoteMap.get(instrument.getInstrumentId());
	}

	public static class AtomicQuote {

		private AtomicDouble askPrice1;
		private AtomicDouble askVolume1;
		private AtomicDouble bidPrice1;
		private AtomicDouble bidVolume1;

		public AtomicQuote() {
			super();
			this.askPrice1 = new AtomicDouble();
			this.askVolume1 = new AtomicDouble();
			this.bidPrice1 = new AtomicDouble();
			this.bidVolume1 = new AtomicDouble();
		}

		public AtomicDouble getAskPrice1() {
			return askPrice1;
		}

		public AtomicDouble getAskVolume1() {
			return askVolume1;
		}

		public AtomicDouble getBidPrice1() {
			return bidPrice1;
		}

		public AtomicDouble getBidVolume1() {
			return bidVolume1;
		}

	}

}
