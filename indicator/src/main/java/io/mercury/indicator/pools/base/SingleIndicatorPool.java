package io.mercury.indicator.pools.base;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.api.Indicator;

public abstract class SingleIndicatorPool<I extends Indicator<?, ?>> extends IndicatorPool<I> {

	private MutableIntObjectMap<I> s1IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> s2IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> s5IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> s10IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> s15IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> s30IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> m1IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> m2IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> m5IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> m10IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	private MutableIntObjectMap<I> m15IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);

	@ProtectedAbstractMethod
	protected abstract I generateIndicator(TimePeriod period, Instrument instrument);

	public I getIndicator(TimePeriod period, Instrument instrument) {
		MutableIntObjectMap<I> indicatorMap = getIndicatorMap(period);
		I saved = indicatorMap.get(instrument.id());
		if (saved == null) {
			saved = generateIndicator(period, instrument);
			indicatorMap.put(instrument.id(), saved);
		}
		return saved;
	}

	public boolean putIndicator(TimePeriod period, Instrument instrument, I indicator) {
		MutableIntObjectMap<I> indicatorMap = getIndicatorMap(period);
		I saved = indicatorMap.get(instrument.id());
		if (saved != null) {
			log.warn("Indicator existed. period==[{}], instrumentCode==[{}]", period, instrument.id());
			return false;
		}
		indicatorMap.put(instrument.id(), indicator);
		return indicators.add(indicator);
	}

	private MutableIntObjectMap<I> getIndicatorMap(TimePeriod period) {
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
