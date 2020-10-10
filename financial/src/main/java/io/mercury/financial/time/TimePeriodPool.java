package io.mercury.financial.time;

import java.time.Duration;
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
	public void register(Symbol symbol, Duration... durations) {
		register(new Symbol[] { symbol }, durations);
	}

	/**
	 * 
	 * @param symbols
	 * @param periods
	 */
	public void register(Symbol[] symbols, Duration... durations) {
		Assertor.requiredLength(symbols, 1, "symbols");
		Assertor.requiredLength(durations, 1, "durations");
		for (Duration duration : durations)
			generateTimePeriod(symbols, duration);
	}

	/**
	 * 
	 * @param symbol
	 * @param period
	 * @return
	 */
	private long mergeSymbolTimeKey(Symbol symbol, Duration duration) {
		return JointKeyParams.mergeJointKey(symbol.id(), (int) duration.getSeconds());
	}

	private void generateTimePeriod(Symbol[] symbols, Duration duration) {
		for (Symbol symbol : symbols) {
			MutableSortedSet<TimePeriodSerial> timePeriodSet = MutableSets.newTreeSortedSet();
			MutableLongObjectMap<TimePeriodSerial> timePeriodMap = MutableMaps.newLongObjectHashMap();
			// 获取指定品种下的全部交易时段,将交易时段按照指定指标周期切分
			symbol.getTradingPeriodSet().stream()
					.flatMap(tradingPeriod -> tradingPeriod.segmentation(symbol.exchange().zoneId(), duration).stream())
					.collect(Collectors.toList()).forEach(serial -> {
						timePeriodSet.add(serial);
						timePeriodMap.put(serial.serialId(), serial);
					});
			long symbolTimeKey = mergeSymbolTimeKey(symbol, duration);
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
	public ImmutableSortedSet<TimePeriodSerial> getTimePeriodSet(Instrument instrument, Duration duration) {
		return getTimePeriodSet(instrument.symbol(), duration);
	}

	/**
	 * 在指定Symbol列表中找出相应的时间分割点信息
	 * 
	 * @param symbolMap
	 * @param period
	 * @param symbol
	 * @return
	 */
	public ImmutableSortedSet<TimePeriodSerial> getTimePeriodSet(Symbol symbol, Duration duration) {
		long symbolTimeKey = mergeSymbolTimeKey(symbol, duration);
		ImmutableSortedSet<TimePeriodSerial> sortedSet = timePeriodSetPool.get(symbolTimeKey);
		if (sortedSet == null) {
			register(symbol, duration);
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
	public ImmutableLongObjectMap<TimePeriodSerial> getTimePeriodMap(Instrument instrument, Duration duration) {
		return getTimePeriodMap(instrument.symbol(), duration);
	}

	/**
	 * 
	 * @param symbol
	 * @param period
	 * @return
	 */
	public ImmutableLongObjectMap<TimePeriodSerial> getTimePeriodMap(Symbol symbol, Duration duration) {
		long symbolTimeKey = mergeSymbolTimeKey(symbol, duration);
		ImmutableLongObjectMap<TimePeriodSerial> longObjectMap = timePeriodMapPool.get(symbolTimeKey);
		if (longObjectMap == null) {
			register(symbol, duration);
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
	public TimePeriodSerial getNextTimePeriod(Instrument instrument, Duration duration, TimePeriodSerial serial) {
		return getNextTimePeriod(instrument.symbol(), duration, serial);
	}

	@CheckForNull
	public TimePeriodSerial getNextTimePeriod(Symbol symbol, Duration duration, TimePeriodSerial serial) {
		ImmutableLongObjectMap<TimePeriodSerial> longObjectMap = getTimePeriodMap(symbol, duration);
		return longObjectMap.get(serial.serialId() + duration.getSeconds());
	}

}
