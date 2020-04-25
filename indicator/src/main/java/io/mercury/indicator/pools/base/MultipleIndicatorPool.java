package io.mercury.indicator.pools.base;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.param.JointIdSupporter;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.api.Indicator;

public abstract class MultipleIndicatorPool<I extends Indicator<?, ?>> extends IndicatorPool<I> {

	private MutableLongObjectMap<I> s1IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> s2IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> s5IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> s10IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> s15IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> s30IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> m1IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> m2IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> m5IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> m10IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);
	private MutableLongObjectMap<I> m15IndicatorMap = MutableMaps.newLongObjectHashMap(Capacity.L04_SIZE_16);

	@Nonnull
	@ProtectedAbstractMethod
	protected abstract I generateIndicator(TimePeriod period, int cycle, Instrument instrument);

	public I getIndicator(TimePeriod period, int cycle, Instrument instrument) {
		MutableLongObjectMap<I> indicatorMap = getIndicatorMap(period);
		long index = calculateIndex(cycle, instrument);
		return indicatorMap.getIfAbsentPut(index, generateIndicator(period, cycle, instrument));
	}

	public boolean putIndicator(TimePeriod period, int cycle, Instrument instrument, I indicator) {
		MutableLongObjectMap<I> indicatorMap = getIndicatorMap(period);
		long index = calculateIndex(cycle, instrument);
		I saved = indicatorMap.get(index);
		if (saved != null) {
			log.warn("Indicator existed. period==[{}], instrumentCode==[{}], cycle==[{}]", period, instrument.id(),
					cycle);
			return false;
		}
		indicatorMap.put(index, indicator);
		return indicators.add(indicator);
	}

	private long calculateIndex(int cycle, Instrument instrument) {
		return JointIdSupporter.jointId(cycle, instrument.id());
	}

	private MutableLongObjectMap<I> getIndicatorMap(TimePeriod period) {
		switch (period) {
		case S1:
			return s1IndicatorMap;
		case S2:
			return s2IndicatorMap;
		case S5:
			return s5IndicatorMap;
		case S10:
			return s10IndicatorMap;
		case S15:
			return s15IndicatorMap;
		case S30:
			return s30IndicatorMap;
		case M1:
			return m1IndicatorMap;
		case M2:
			return m2IndicatorMap;
		case M5:
			return m5IndicatorMap;
		case M10:
			return m10IndicatorMap;
		case M15:
			return m15IndicatorMap;
		default:
			throw new IllegalArgumentException("period : " + period.name() + " is not found");
		}
	}

}
