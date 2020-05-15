package io.mercury.redstone.core.keeper;

import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.io.Dumper;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;

/**
 * 管理当前最新行情<br>
 * 
 * 仅在初始化时使用InstrumentKeeper加载一次Instrument<br>
 * 
 * 无论修改最新行情或查询最新行情都使用Get方法获取对象<br>
 * 对象使用原子类型保证
 * 
 * @creation 2019年4月16日
 * 
 * @author yellow013
 */
@ThreadSafe
public final class LastMarkerDataKeeper implements Dumper<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145644316828652275L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(LastMarkerDataKeeper.class);

	/**
	 * LastMarkerData Map
	 */
	private final ImmutableIntObjectMap<LastMarkerData> immutableQuoteMap;

	private final static LastMarkerDataKeeper StaticInstance = new LastMarkerDataKeeper();

	private LastMarkerDataKeeper() {
		MutableIntObjectMap<LastMarkerData> mutableQuoteMap = MutableMaps.newIntObjectHashMap();
		ImmutableList<Instrument> allInstrument = InstrumentKeeper.getAllInstrument();
		if (allInstrument.isEmpty())
			throw new IllegalStateException("InstrumentKeeper is uninitialized");
		allInstrument.each(instrument -> {
			mutableQuoteMap.put(instrument.id(), new LastMarkerData());
			log.info("Add instrument, instrumentId==[{}], instrument -> {}", instrument.id(), instrument);
		});
		immutableQuoteMap = mutableQuoteMap.toImmutable();
	}

	public static void onMarketDate(BasicMarketData marketData) {
		Instrument instrument = marketData.instrument();
		LastMarkerData lastMarkerData = get(instrument);
		if (lastMarkerData == null) {
			log.warn("Instrument unregistered, instrument -> {}", instrument);
		} else {
			lastMarkerData.setAskPrice1(marketData.getAskPrice1()).setAskVolume1(marketData.getAskVolume1())
					.setBidPrice1(marketData.getBidPrice1()).setBidVolume1(marketData.getBidVolume1());
		}
	}

	public static LastMarkerData get(Instrument instrument) {
		return StaticInstance.immutableQuoteMap.get(instrument.id());
	}

	public static class LastMarkerData {

		private volatile long askPrice1;
		private volatile int askVolume1;
		private volatile long bidPrice1;
		private volatile int bidVolume1;

		private LastMarkerData() {
		}

		public long askPrice1() {
			return askPrice1;
		}

		public int askVolume1() {
			return askVolume1;
		}

		public long bidPrice1() {
			return bidPrice1;
		}

		public int bidVolume1() {
			return bidVolume1;
		}

		private LastMarkerData setAskPrice1(long askPrice1) {
			this.askPrice1 = askPrice1;
			return this;
		}

		private LastMarkerData setAskVolume1(int askVolume1) {
			this.askVolume1 = askVolume1;
			return this;
		}

		private LastMarkerData setBidPrice1(long bidPrice1) {
			this.bidPrice1 = bidPrice1;
			return this;
		}

		private LastMarkerData setBidVolume1(int bidVolume1) {
			this.bidVolume1 = bidVolume1;
			return this;
		}

	}

	@Override
	public String dump() {
		// TODO Auto-generated method stub
		return null;
	}

}
