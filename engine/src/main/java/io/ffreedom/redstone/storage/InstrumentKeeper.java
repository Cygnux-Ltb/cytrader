package io.ffreedom.redstone.storage;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.polaris.financial.Instrument;

//TODO 修改为不可变类型
public final class InstrumentKeeper {

	// 存储instrument的交易状态,以instrumentId索引
	private MutableIntBooleanMap tradableMap = new IntBooleanHashMap();

	// 存储instrument,以instrumentId索引
	private MutableIntObjectMap<Instrument> instrumentIdMap = MutableMaps.newIntObjectHashMap();

	// 存储instrument,以instrumentCode索引
	private MutableMap<String, Instrument> instrumentCodeMap = MutableMaps.newUnifiedMap();

	private final static InstrumentKeeper InnerInstance = new InstrumentKeeper();

	private InstrumentKeeper() {
	}

	public static void setNotTradable(int instrumentId) {
		getInstrument(instrumentId).disable();
	}

	public static void setTradable(int instrumentId) {
		getInstrument(instrumentId).enable();
	}

	public static boolean isTradable(int instrumentId) {
		return InnerInstance.tradableMap.get(instrumentId);
	}

	public static void putInstrument(Instrument instrument) {
		InnerInstance.instrumentIdMap.put(instrument.getInstrumentId(), instrument);
		InnerInstance.instrumentCodeMap.put(instrument.getInstrumentCode(), instrument);
		setTradable(instrument.getInstrumentId());
	}

	public static Instrument getInstrument(int instrumentId) {
		Instrument instrument = InnerInstance.instrumentIdMap.get(instrumentId);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, instrumentId == " + instrumentId);
		return instrument;
	}

	public static Instrument getInstrument(String instrumentCode) {
		Instrument instrument = InnerInstance.instrumentCodeMap.get(instrumentCode);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, instrumentCode ==" + instrumentCode);
		return instrument;
	}

	public static ImmutableList<Instrument> getAllInstrument() {
		return InnerInstance.instrumentIdMap.toList().toImmutable();
	}

}
