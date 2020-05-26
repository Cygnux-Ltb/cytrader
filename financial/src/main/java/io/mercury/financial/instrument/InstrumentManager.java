package io.mercury.financial.instrument;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.io.Dumper;
import io.mercury.common.log.CommonLoggerFactory;

/**
 * 
 * 管理全局标的状态
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class InstrumentManager implements Dumper<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6884791803809601376L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentManager.class);

	/**
	 * 存储instrument,以instrumentId索引
	 */
	private static final MutableIntObjectMap<Instrument> InstrumentMapById = MutableMaps.newIntObjectHashMap();

	/**
	 * 存储instrument,以instrumentCode索引
	 */
	private static final MutableMap<String, Instrument> InstrumentMapByCode = MutableMaps.newUnifiedMap();

	private InstrumentManager() {
	}

	private static final boolean isInit = false;

	public static void initialize(@Nonnull Instrument... instruments) {
		if (!isInit) {
			for (Instrument instrument : instruments) {
				log.info("Put instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}", instrument.id(),
						instrument.code(), instrument);
				InstrumentMapById.put(instrument.id(), instrument);
				InstrumentMapByCode.put(instrument.code(), instrument);
				setTradable(instrument.id());
			}
		} else {
			log.error("InstrumentManager Has been initialized, cannot be initialize again");
		}
	}

	public static Instrument setTradable(Instrument instrument) {
		return setTradable(instrument.id());
	}

	public static Instrument setTradable(int instrumentId) {
		Instrument instrument = getInstrument(instrumentId);
		log.info("Instrument enable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
		return instrument.enable();
	}

	public static Instrument setNotTradable(Instrument instrument) {
		return setNotTradable(instrument.id());
	}

	public static Instrument setNotTradable(int instrumentId) {
		Instrument instrument = getInstrument(instrumentId);
		log.info("Instrument disable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
		return instrument.disable();
	}

	public static boolean isTradable(Instrument instrument) {
		return isTradable(instrument.id());
	}

	public static boolean isTradable(int instrumentId) {
		return getInstrument(instrumentId).isEnabled();
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

	@Override
	public String dump() {
		return "";
	}

}
