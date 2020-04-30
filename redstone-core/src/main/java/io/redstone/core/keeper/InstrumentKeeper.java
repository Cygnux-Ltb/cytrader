package io.redstone.core.keeper;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;

public final class InstrumentKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentKeeper.class);

	// 存储instrument,以instrumentId索引
	private static final MutableIntObjectMap<Instrument> InstrumentIdMap = MutableMaps.newIntObjectHashMap();

	// 存储instrument,以instrumentCode索引
	private static final MutableMap<String, Instrument> InstrumentCodeMap = MutableMaps.newUnifiedMap();

	private InstrumentKeeper() {
	}

	public static void setTradable(Instrument instrument) {
		getInstrument(instrument.id()).enable();
	}

	public static void setTradable(int instrumentId) {
		getInstrument(instrumentId).enable();
	}

	public static void setNotTradable(Instrument instrument) {
		getInstrument(instrument.id()).disable();
	}

	public static void setNotTradable(int instrumentId) {
		getInstrument(instrumentId).disable();
	}

	public static boolean isTradable(Instrument instrument) {
		return getInstrument(instrument.id()).isEnabled();
	}

	public static boolean isTradable(int instrumentId) {
		return getInstrument(instrumentId).isEnabled();
	}

	public static void putInstrument(Instrument instrument) {
		log.info("InstrumentKeeper :: Put instrument, id==[{}], code==[{}], instrument -> {}", instrument.id(),
				instrument.code(), instrument);
		InstrumentIdMap.put(instrument.id(), instrument);
		InstrumentCodeMap.put(instrument.code(), instrument);
		setTradable(instrument.id());
	}

	public static Instrument getInstrument(int instrumentId) {
		Instrument instrument = InstrumentIdMap.get(instrumentId);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, instrumentId == " + instrumentId);
		return instrument;
	}

	public static Instrument getInstrument(String instrumentCode) {
		Instrument instrument = InstrumentCodeMap.get(instrumentCode);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, instrumentCode == " + instrumentCode);
		return instrument;
	}

	public static ImmutableList<Instrument> getAllInstrument() {
		return InstrumentIdMap.toList().toImmutable();
	}

}
