package io.ffreedom.redstone.keeper;

import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.ffreedom.common.functional.Callback;
import io.ffreedom.polaris.market.Quotes;

@NotThreadSafe
public class QuoteKeeper {

	// Map<InstrumentId, Quotes>
	private MutableIntObjectMap<Quotes> quotesMap = IntObjectHashMap.newMap();

	public static final QuoteKeeper INSTANCE = new QuoteKeeper();

	private QuoteKeeper() {
	}

	public void putQuotes(int instrumentId, Quotes quotes) {
		quotesMap.put(instrumentId, quotes);
	}

	public Optional<Quotes> getQuotes(int instrumentId) {
		return Optional.ofNullable(quotesMap.get(instrumentId));
	}

	public void updateQuotes(int instrumentId, QuotesUpdater updater) {
		updater.updateQuotes(getQuotes(instrumentId).orElseGet(() -> {
			// TODO Quotes level set def level 10
			Quotes quotes = Quotes.newInstance();
			putQuotes(instrumentId, quotes);
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
