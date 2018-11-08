package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.ffreedom.financial.Instrument;

public final class InstrumentState {

	private MutableIntBooleanMap isTradableMap = new IntBooleanHashMap();

	private MutableIntObjectMap<Instrument> instrumentIdMap = IntObjectHashMap.newMap();

	private MutableMap<String, Instrument> instrumentCodeMap = UnifiedMap.newMap();

	private final static InstrumentState INSTANCE = new InstrumentState();

	private InstrumentState() {
	}

	public static void setNontradable(Instrument instrument) {
		INSTANCE.setNontradable0(instrument);
	}

	private void setNontradable0(Instrument instrument) {
		isTradableMap.put(instrument.getInstrumentId(), false);
	}

	public static void setTradable(Instrument instrument) {
		INSTANCE.setTradable0(instrument);
	}

	private void setTradable0(Instrument instrument) {
		isTradableMap.put(instrument.getInstrumentId(), true);
	}

	public boolean isTradable(Instrument instrument) {
		return INSTANCE.isTradable0(instrument);
	}

	private boolean isTradable0(Instrument instrument) {
		return isTradableMap.get(instrument.getInstrumentId());
	}

	public static void putInstrument(Instrument instrument) {
		INSTANCE.putInstrument0(instrument);
	}

	private void putInstrument0(Instrument instrument) {
		instrumentIdMap.put(instrument.getInstrumentId(), instrument);
		instrumentCodeMap.put(instrument.getInstrumentCode(), instrument);
		setTradable(instrument);
	}

	public static Instrument getInstrument(int instrumentId) {
		return INSTANCE.getInstrument0(instrumentId);
	}

	private Instrument getInstrument0(int instrumentId) {
		Instrument instrument = instrumentIdMap.get(instrumentId);
		if (instrument == null) {
			throw new IllegalArgumentException("InstrumentId -> " + instrumentId + " is not find.");
		}
		return instrument;
		// return Optional.ofNullable(instrumentMap.get(instrumentId))
		// .orElseThrow(() -> new IllegalArgumentException(""));
	}

	public static Instrument getInstrument(String instrumentCode) {
		return INSTANCE.getInstrument0(instrumentCode);
	}

	private Instrument getInstrument0(String instrumentCode) {
		Instrument instrument = instrumentCodeMap.get(instrumentCode);
		if (instrument == null) {
			throw new IllegalArgumentException("InstrumentCode -> " + instrumentCode + " is not find.");
		}
		return instrument;
		// return Optional.ofNullable(instrumentMap.get(instrumentId))
		// .orElseThrow(() -> new IllegalArgumentException(""));
	}

}
