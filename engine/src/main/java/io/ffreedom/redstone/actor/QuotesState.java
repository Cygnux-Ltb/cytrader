package io.ffreedom.redstone.actor;

import java.util.Optional;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.ffreedom.common.functional.Callback;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.Quotes;

public class QuotesState {

	// Map<InstrumentId, Quotes>
	private MutableIntObjectMap<Quotes> quotesMap = IntObjectHashMap.newMap();

	public static final QuotesState INSTANCE = new QuotesState();

	private QuotesState() {
	}

	public void putQuotes(Instrument instrument, Quotes quotes) {
		quotesMap.put(instrument.getInstrumentId(), quotes);
	}

	public Optional<Quotes> getQuotes(Instrument instrument) {
		return Optional.ofNullable(quotesMap.get(instrument.getInstrumentId()));
	}

	public void updateQuotes(Instrument instrument, QuotesUpdater updater) {
		updater.updateQuotes(getQuotes(instrument).orElseGet(() -> {
			// TODO Quotes level set def level 10
			Quotes quotes = Quotes.newInstance();
			putQuotes(instrument, quotes);
			return quotes;
		}));
	}

	@FunctionalInterface
	public static interface QuotesUpdater extends Callback<Quotes> {
		default void updateQuotes(Quotes quotes) {
			onEvent(quotes);
		}
	}

	public static void main(String[] args) {

		MutableIntObjectMap<String> quotesMap = IntObjectHashMap.newMap();

		String put = quotesMap.put(0, "AAA");
		String put1 = quotesMap.put(0, "AAAXX");
		String put2 = quotesMap.put(0, "AAAXXDD");

		System.out.println(put);
		System.out.println(put1);
		System.out.println(put2);

	}

}
