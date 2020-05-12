package io.redstone.core.keeper;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;

/**
 * 
 * 管理全局标的状态
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class InstrumentKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentKeeper.class);

	// 存储instrument,以instrumentId索引
	private static final MutableIntObjectMap<Instrument> InstrumentMapById = MutableMaps.newIntObjectHashMap();

	// 存储instrument,以instrumentCode索引
	private static final MutableMap<String, Instrument> InstrumentMapByCode = MutableMaps.newUnifiedMap();

	private InstrumentKeeper() {
	}

	public static void setTradable(Instrument instrument) {
		setTradable(instrument.id());
	}

	public static Instrument setTradable(int instrumentId) {
		Instrument instrument = getInstrument(instrumentId);
		log.info("InstrumentKeeper :: Instrument enable, instrumentId==[{}], instrument -> {}", instrumentId,
				instrument);
		return instrument.enable();
	}

	public static void setNotTradable(Instrument instrument) {
		setNotTradable(instrument.id());
	}

	public static void setNotTradable(int instrumentId) {
		Instrument instrument = getInstrument(instrumentId);
		instrument.disable();
		log.info("InstrumentKeeper :: Instrument disable, instrumentId==[{}], instrument -> {}", instrumentId,
				instrument);
	}

	public static boolean isTradable(Instrument instrument) {
		return isTradable(instrument.id());
	}

	public static boolean isTradable(int instrumentId) {
		return getInstrument(instrumentId).isEnabled();
	}

	public static void putInstrument(Instrument instrument) {
		log.info("InstrumentKeeper :: Put instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}",
				instrument.id(), instrument.code(), instrument);
		InstrumentMapById.put(instrument.id(), instrument);
		InstrumentMapByCode.put(instrument.code(), instrument);
		setTradable(instrument.id());
	}

	public static Instrument getInstrument(int instrumentId) {
		Instrument instrument = InstrumentMapById.get(instrumentId);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, instrumentId == " + instrumentId);
		return instrument;
	}

	public static Instrument getInstrument(String instrumentCode) {
		Instrument instrument = InstrumentMapByCode.get(instrumentCode);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, instrumentCode == " + instrumentCode);
		return instrument;
	}

	public static ImmutableList<Instrument> getAllInstrument() {
		return InstrumentMapById.toList().toImmutable();
	}

}
