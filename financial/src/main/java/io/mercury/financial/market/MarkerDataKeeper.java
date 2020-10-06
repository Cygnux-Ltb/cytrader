package io.mercury.financial.market;

import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.serialization.Dumpable;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.market.api.MarketData;
import io.mercury.serialization.json.JsonUtil;

/**
 * 管理当前最新行情<br>
 * 
 * 仅在初始化时使用InstrumentKeeper加载一次Instrument<br>
 * 
 * 无论修改最新行情或查询最新行情都使用GetLast方法获取对象<br>
 * 对象使用原子类型保证
 * 
 * @creation 2019年4月16日
 * 
 * @author yellow013
 */
@ThreadSafe
public final class MarkerDataKeeper implements Dumpable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145644316828652275L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(MarkerDataKeeper.class);

	/**
	 * LastMarkerData Map
	 */
	private final ImmutableMap<String, LastMarkerData> QuoteMap;

	private final static MarkerDataKeeper StaticInstance = new MarkerDataKeeper();

	private MarkerDataKeeper() {
		MutableMap<String, LastMarkerData> tempQuoteMap = MutableMaps.newUnifiedMap();
		ImmutableList<Instrument> allInstrument = InstrumentManager.allInstrument();
		if (allInstrument.isEmpty())
			throw new IllegalStateException("InstrumentKeeper is uninitialized");
		allInstrument.each(instrument -> {
			tempQuoteMap.put(instrument.code(), new LastMarkerData());
			log.info("Add instrument, instrumentId==[{}], instrument -> {}", instrument.id(), instrument);
		});
		QuoteMap = tempQuoteMap.toImmutable();
	}

	public static void onMarketDate(MarketData marketData) {
		String instrumentCode = marketData.getInstrumentCode();
		LastMarkerData lastMarkerData = getLast(instrumentCode);
		if (lastMarkerData == null)
			log.warn("Instrument unregistered, instrumentCode -> {}", instrumentCode);
		else
			lastMarkerData.setAskPrice1(marketData.getAskPrice1()).setAskVolume1(marketData.getAskVolume1())
					.setBidPrice1(marketData.getBidPrice1()).setBidVolume1(marketData.getBidVolume1());
	}

	public static LastMarkerData getLast(Instrument instrument) {
		return getLast(instrument.code());
	}

	public static LastMarkerData getLast(String instrumentCode) {
		return StaticInstance.QuoteMap.get(instrumentCode);
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
		return JsonUtil.toPrettyJsonHasNulls(QuoteMap);
	}

}
