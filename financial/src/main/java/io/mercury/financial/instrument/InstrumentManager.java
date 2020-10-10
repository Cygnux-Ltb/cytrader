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
import io.mercury.common.util.Assertor;
import io.mercury.financial.instrument.futures.impl.ChinaFutures;
import io.mercury.financial.instrument.futures.impl.ChinaFuturesSymbol;
import io.mercury.serialization.json.JsonUtil;

/**
 * 
 * 管理全局标的状态
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class InstrumentManager {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentManager.class);

	/**
	 * 存储instrument, 以instrumentId索引
	 */
	private static final MutableIntObjectMap<Instrument> InstrumentMapById = MutableMaps.newIntObjectHashMap();

	/**
	 * 存储instrument, 以instrumentCode索引
	 */
	private static final MutableMap<String, Instrument> InstrumentMapByCode = MutableMaps.newUnifiedMap();

	private InstrumentManager() {
	}

	private static volatile boolean isInitialized = false;

	public static synchronized void initialize(@Nonnull Instrument... instruments) {
		if (!isInitialized) {
			Assertor.requiredLength(instruments, 1, "instruments");
			Stream.of(instruments).forEach(InstrumentManager::putInstrument);
			isInitialized = true;
		} else {
			IllegalStateException stateException = new IllegalStateException(
					"InstrumentManager Has been initialized, cannot be initialize again.");
			log.error("InstrumentManager already initialized.", stateException);
			throw stateException;
		}
	}

	private static void putInstrument(Instrument instrument) {
		log.info("Put instrument, instrumentId==[{}], instrumentCode==[{}], instrument -> {}", instrument.id(),
				instrument.code(), instrument);
		InstrumentMapById.put(instrument.id(), instrument);
		InstrumentMapByCode.put(instrument.code(), instrument);
		setTradable(instrument);
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
	public static Instrument setTradable(int instrumentId) {
		Instrument instrument = getInstrument(instrumentId);
		log.info("Instrument enable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
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
	 * @param instrumentId
	 * @return
	 */
	public static Instrument setNotTradable(int instrumentId) {
		Instrument instrument = getInstrument(instrumentId);
		log.info("Instrument disable, instrumentId==[{}], instrument -> {}", instrumentId, instrument);
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
	public static boolean isTradable(int instrumentId) {
		return getInstrument(instrumentId).isEnabled();
	}

	/**
	 * 
	 * @param instrumentId
	 * @return
	 */
	public static Instrument[] getInstrument(int... instrumentIds) {
		Assertor.requiredLength(instrumentIds, 1, "instrumentIds");
		Instrument[] instruments = new Instrument[instrumentIds.length];
		for (int i = 0; i < instrumentIds.length; i++) {
			instruments[i] = getInstrument(instrumentIds[i]);
		}
		return instruments;
	}

	/**
	 * 
	 * @param instrumentId
	 * @return
	 */
	public static Instrument getInstrument(int instrumentId) {
		Instrument instrument = InstrumentMapById.get(instrumentId);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, by instrumentId : " + instrumentId);
		return instrument;
	}

	/**
	 * 
	 * @param instrumentCodes
	 * @return
	 */
	public static Instrument[] getInstrument(String... instrumentCodes) {
		Assertor.requiredLength(instrumentCodes, 1, "instrumentCodes");
		Instrument[] instruments = new Instrument[instrumentCodes.length];
		for (int i = 0; i < instrumentCodes.length; i++) {
			instruments[i] = getInstrument(instrumentCodes[i]);
		}
		return instruments;
	}

	/**
	 * 
	 * @param instrumentCode
	 * @return
	 */
	public static Instrument getInstrument(String instrumentCode) {
		Instrument instrument = InstrumentMapByCode.get(instrumentCode);
		if (instrument == null)
			throw new IllegalArgumentException("Instrument is not find, by instrumentCode : " + instrumentCode);
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

	public static String showStatus() {
		Map<String, Object> map = new HashMap<>();
		map.put("isInitialized", isInitialized);
		map.put("instruments", allInstrument());
		return JsonUtil.toPrettyJson(map);
	}

	public static void main(String[] args) {
		ChinaFutures au2012 = new ChinaFutures(ChinaFuturesSymbol.AU, 2012, "2012");
		ChinaFutures rb2101 = new ChinaFutures(ChinaFuturesSymbol.RB, 2101, "2101");
		InstrumentManager.initialize(au2012, rb2101);
		System.out.println(InstrumentManager.showStatus());
		InstrumentManager.setNotTradable(rb2101);
		System.out.println(InstrumentManager.showStatus());
	}

}
