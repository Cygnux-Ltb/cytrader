package io.mercury.financial.time;

import java.util.stream.Collectors;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.ImmutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.param.JointKeyParams;
import io.mercury.common.util.Assertor;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

@NotThreadSafe
public final class TimePeriodPool {

	public static final TimePeriodPool Singleton = new TimePeriodPool();

	private TimePeriodPool() {
	}

	/**
	 * 使用联合主键进行索引,高位为symbolId, 低位为period <br>
	 * 可变的Pool,最终元素为Set <br>
	 * Map<(period + symbolId), Set<TimePeriod>>
	 */
	private MutableLongObjectMap<ImmutableSortedSet<TimePeriodSerial>> timePeriodSetPool = MutableMaps
			.newLongObjectHashMap();

	/**
	 * 使用联合主键进行索引,高位为symbolId, 低位为period <br>
	 * 可变的Pool,最终元素为Map <br>
	 * Map<(period + symbolId), Map<SerialNumber,TimePeriod>>
	 */
	private MutableLongObjectMap<ImmutableLongObjectMap<TimePeriodSerial>> timePeriodMapPool = MutableMaps
			.newLongObjectHashMap();

	/**
	 * 
	 * @param symbol
	 * @param periods
	 */
	public void register(Symbol symbol, TimePeriod... periods) {
		register(new Symbol[] { symbol }, periods);
	}

	/**
	 * 
	 * @param symbols
	 * @param periods
	 */
	public void register(Symbol[] symbols, TimePeriod... periods) {
		Assertor.requiredLength(symbols, 1, "symbols");
		Assertor.requiredLength(periods, 1, "periods");
		for (TimePeriod period : periods)
			generateTimePeriod(symbols, period);
	}

	/**
	 * 
	 * @param symbol
	 * @param period
	 * @return
	 */
	private long mergeSymbolTimeKey(Symbol symbol, TimePeriod period) {
		return JointKeyParams.mergeJointKey(symbol.id(), period.seconds());
	}

	private void generateTimePeriod(Symbol[] symbols, TimePeriod period) {
		for (Symbol symbol : symbols) {
			MutableSortedSet<TimePeriodSerial> timePeriodSet = MutableSets.newTreeSortedSet();
			MutableLongObjectMap<TimePeriodSerial> timePeriodMap = MutableMaps.newLongObjectHashMap();
			// 获取指定品种下的全部交易时段,将交易时段按照指定指标周期切分
			symbol.tradingPeriodSet().stream()
					.flatMap(tradingPeriod -> tradingPeriod.segmentation(symbol.exchange().zoneId(), period).stream())
					.collect(Collectors.toList()).forEach(serial -> {
						timePeriodSet.add(serial);
						timePeriodMap.put(serial.serialId(), serial);
					});
			long symbolTimeKey = mergeSymbolTimeKey(symbol, period);
			timePeriodSetPool.put(symbolTimeKey, timePeriodSet.toImmutable());
			timePeriodMapPool.put(symbolTimeKey, timePeriodMap.toImmutable());
		}
	}

	/**
	 * 获取指定Instrument和指定指标周期下的全部时间分割点
	 * 
	 * @param period
	 * @param symbol
	 * @return
	 */
	public ImmutableSortedSet<TimePeriodSerial> getTimePeriodSet(Instrument instrument, TimePeriod period) {
		return getTimePeriodSet(instrument.symbol(), period);
	}

	/**
	 * 在指定Symbol列表中找出相应的时间分割点信息
	 * 
	 * @param symbolMap
	 * @param period
	 * @param symbol
	 * @return
	 */
	public ImmutableSortedSet<TimePeriodSerial> getTimePeriodSet(Symbol symbol, TimePeriod period) {
		long symbolTimeKey = mergeSymbolTimeKey(symbol, period);
		ImmutableSortedSet<TimePeriodSerial> sortedSet = timePeriodSetPool.get(symbolTimeKey);
		if (sortedSet == null) {
			register(symbol, period);
			sortedSet = timePeriodSetPool.get(symbolTimeKey);
		}
		return sortedSet;
	}

	/**
	 * 
	 * @param instrument
	 * @param period
	 * @return
	 */
	public ImmutableLongObjectMap<TimePeriodSerial> getTimePeriodMap(Instrument instrument, TimePeriod period) {
		return getTimePeriodMap(instrument.symbol(), period);
	}

	/**
	 * 
	 * @param symbol
	 * @param period
	 * @return
	 */
	public ImmutableLongObjectMap<TimePeriodSerial> getTimePeriodMap(Symbol symbol, TimePeriod period) {
		long symbolTimeKey = mergeSymbolTimeKey(symbol, period);
		ImmutableLongObjectMap<TimePeriodSerial> longObjectMap = timePeriodMapPool.get(symbolTimeKey);
		if (longObjectMap == null) {
			register(symbol, period);
			longObjectMap = timePeriodMapPool.get(symbolTimeKey);
		}
		return longObjectMap;
	}

	/**
	 * 
	 * @param instrument
	 * @param period
	 * @param serial
	 * @return
	 */
	public TimePeriodSerial getNextTimePeriod(Instrument instrument, TimePeriod period, TimePeriodSerial serial) {
		return getNextTimePeriod(instrument.symbol(), period, serial);
	}

	@CheckForNull
	public TimePeriodSerial getNextTimePeriod(Symbol symbol, TimePeriod period, TimePeriodSerial serial) {
		ImmutableLongObjectMap<TimePeriodSerial> longObjectMap = getTimePeriodMap(symbol, period);
		return longObjectMap.get(serial.serialId() + period.seconds());
	}

}
