package io.redstone.engine.actor;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.polaris.financial.instrument.Instrument;
import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.utils.PriceUtil;
import io.redstone.engine.actor.QuoteActor.AtomicQuote;
import io.redstone.engine.storage.InstrumentKeeper;

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
			allInstrument.forEach(instrument -> tempQuoteMap.put(instrument.id(), new AtomicQuote()));
		quoteMap = tempQuoteMap.toImmutable();
	}

	public void onMarketDate(BasicMarketData marketData) {
		AtomicQuote atomicQuote = quoteMap.get(marketData.getInstrument().id());
		atomicQuote.getAskPrice1().set(PriceUtil.doublePriceToLong(marketData.getAskPrice1()));
		atomicQuote.getAskVolume1().set(marketData.getAskVolume1());
		atomicQuote.getBidPrice1().set(PriceUtil.doublePriceToLong(marketData.getBidPrice1()));
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
