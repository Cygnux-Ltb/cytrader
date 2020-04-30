package io.redstone.core.keeper;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;

/**
 * 管理当前最新行情
 * 
 * @creation 2019年4月16日
 */
public final class LastMarkerDataKeeper {

	private final ImmutableIntObjectMap<LastMarkerData> quoteMap;

	private final static LastMarkerDataKeeper InnerInstance = new LastMarkerDataKeeper();

	private LastMarkerDataKeeper() {
		MutableIntObjectMap<LastMarkerData> tempQuoteMap = MutableMaps.newIntObjectHashMap();
		ImmutableList<Instrument> allInstrument = InstrumentKeeper.getAllInstrument();
		if (allInstrument.isEmpty())
			throw new IllegalStateException("InstrumentKeeper is uninitialized");
		allInstrument.each(instrument -> tempQuoteMap.put(instrument.id(), new LastMarkerData()));
		quoteMap = tempQuoteMap.toImmutable();
	}

	public static void onMarketDate(BasicMarketData marketData) {
		LastMarkerData lastMarkerData = InnerInstance.quoteMap.get(marketData.instrument().id());
		if (lastMarkerData == null)
			return;
		lastMarkerData.askPrice1().set(marketData.getAskPrice1());
		lastMarkerData.askVolume1().set(marketData.getAskVolume1());
		lastMarkerData.bidPrice1().set(marketData.getBidPrice1());
		lastMarkerData.bidVolume1().set(marketData.getBidVolume1());
	}

	public static LastMarkerData get(Instrument instrument) {
		return InnerInstance.quoteMap.get(instrument.id());
	}

	public static class LastMarkerData {

		private AtomicLong askPrice1;
		private AtomicLong askVolume1;
		private AtomicLong bidPrice1;
		private AtomicLong bidVolume1;

		public LastMarkerData() {
			this.askPrice1 = new AtomicLong();
			this.askVolume1 = new AtomicLong();
			this.bidPrice1 = new AtomicLong();
			this.bidVolume1 = new AtomicLong();
		}

		public AtomicLong askPrice1() {
			return askPrice1;
		}

		public AtomicLong askVolume1() {
			return askVolume1;
		}

		public AtomicLong bidPrice1() {
			return bidPrice1;
		}

		public AtomicLong bidVolume1() {
			return bidVolume1;
		}

	}

}
