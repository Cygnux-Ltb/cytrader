package io.mercury.financial.indicator.pools.base;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.financial.indicator.api.Indicator;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.TimePeriod;

public abstract class SingleIndicatorPool<I extends Indicator<?, ?, M>, M extends MarketData>
		extends IndicatorPool<I, M> {

	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> s1IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> s2IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> s5IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> s10IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> s15IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> s30IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> m1IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> m2IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> m5IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
	private MutableIntObjectMap<I> m10IndicatorMap = MutableMaps.newIntObjectHashMap(Capacity.L04_SIZE_16);
	@SuppressWarnings("unused")
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
		// TODO
		throw new IllegalArgumentException("period : " + period + " is not found");
	}

}
