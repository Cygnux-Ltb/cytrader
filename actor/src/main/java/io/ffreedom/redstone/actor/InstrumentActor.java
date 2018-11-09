package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.financial.Instrument;

public final class InstrumentActor {

	// Map<instrumentId, boolean>
	private MutableIntBooleanMap isTradableMap = new IntBooleanHashMap();

	// Map<instrumentId, instrument>
	private MutableIntObjectMap<Instrument> instrumentIdMap = EclipseCollections.newIntObjectHashMap();

	// Map<instrumentCode, instrument>
	private MutableMap<String, Instrument> instrumentCodeMap = EclipseCollections.newUnifiedMap();

	private final static InstrumentActor INSTANCE = new InstrumentActor();

	private InstrumentActor() {
	}

	public static void setNotTradable(Instrument instrument) {
		INSTANCE.notTradable(instrument);
	}

	private void notTradable(Instrument instrument) {
		isTradableMap.put(instrument.getInstrumentId(), false);
	}

	public static void setTradable(Instrument instrument) {
		INSTANCE.tradable(instrument);
	}

	private void tradable(Instrument instrument) {
		isTradableMap.put(instrument.getInstrumentId(), true);
	}

	public static boolean isTradable(Instrument instrument) {
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
		tradable(instrument);
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
