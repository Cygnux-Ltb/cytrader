package io.mercury.financial.instrument;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.serialization.Dumpable;
import io.mercury.common.util.Assertor;
import io.mercury.serialization.json.JsonUtil;

/**
 * 
 * 管理全局标的状态
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class InstrumentManager implements Dumpable<String> {

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

	private static volatile boolean isInitialized = false;

	public static void initialize(@Nonnull Instrument... instruments) {
		if (!isInitialized) {
			Assertor.requiredLength(instruments, 1, "instruments");
			Stream.of(instruments).forEach(InstrumentManager::putInstrument);
			isInitialized = true;
		} else {
			IllegalStateException e = new IllegalStateException(
					"InstrumentManager Has been initialized, cannot be initialize again");
			log.error("InstrumentManager already initialized", e);
			throw e;
		}
	}

	private static void putInstrument(Instrument instrument) {
		log.info("Put instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}", instrument.id(),
				instrument.code(), instrument);
		InstrumentMapById.put(instrument.id(), instrument);
		InstrumentMapByCode.put(instrument.code(), instrument);
		setTradable(instrument.id());
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * 
	 * @param instrument
	 * @return
	 */
	public static Instrument setTradable(Instrument instrument) {
		return setTradable(instrument.id());
	}

	/**
	 * 
	 * @param instrumentId
	 * @return
	 */
	public static Instrument setTradable(int id) {
		Instrument instrument = getInstrument(id);
		log.info("Instrument enable, id==[{}], instrument -> {}", id, instrument);
		return instrument.enable();
	}

	/**
	 * 
	 * @param instrument
	 * @return
	 */
	public static Instrument setNotTradable(Instrument instrument) {
		return setNotTradable(instrument.id());
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Instrument setNotTradable(int id) {
		Instrument instrument = getInstrument(id);
		log.info("Instrument disable, id==[{}], instrument -> {}", id, instrument);
		return instrument.disable();
	}

	/**
	 * 
	 * @param instrument
	 * @return
	 */
	public static boolean isTradable(Instrument instrument) {
		return isTradable(instrument.id());
	}

	/**
	 * 
	 * @param instrumentId
	 * @return
	 */
	public static boolean isTradable(int id) {
		return getInstrument(id).isEnabled();
	}

	/**
	 * 
	 * @param instrumentId
	 * @return
	 */
	public static Instrument getInstrument(int id) {
		Instrument instrument = InstrumentMapById.get(id);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, by instrument id == " + id);
		return instrument;
	}

	/**
	 * 
	 * @param instrumentCode
	 * @return Instrument
	 */
	public static Instrument getInstrument(String code) {
		Instrument instrument = InstrumentMapByCode.get(code);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, by instrument code == " + code);
		return instrument;
	}

	private static volatile ImmutableList<Instrument> allInstrument;

	/**
	 * 
	 * @return ImmutableList
	 */
	public static ImmutableList<Instrument> allInstrument() {
		if (allInstrument == null)
			allInstrument = InstrumentMapById.toList().toImmutable();
		return allInstrument;
	}

	public String dump() {
		String jsonInstrumentMapById = JsonUtil.toPrettyJsonHasNulls(InstrumentMapById);
		String jsonInstrumentMapByCode = JsonUtil.toPrettyJsonHasNulls(InstrumentMapByCode);
		Map<String, String> map = new HashMap<>();
		map.put("InstrumentMapById", jsonInstrumentMapById);
		map.put("InstrumentMapByCode", jsonInstrumentMapByCode);
		return JsonUtil.toPrettyJsonHasNulls(map);
	}

}
