package io.redstone.engine.storage;

import java.util.function.Consumer;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.mercury.polaris.financial.market.impl.Quotes;

@NotThreadSafe
public final class QuoteKeeper {

	// Map<InstrumentId, Quotes>
	private MutableIntObjectMap<Quotes> quotesMap = IntObjectHashMap.newMap();

	public static final QuoteKeeper INSTANCE = new QuoteKeeper();

	private QuoteKeeper() {
	}

	public static void put(int instrumentId, Quotes quotes) {
		INSTANCE.quotesMap.put(instrumentId, quotes);
	}

	public static Quotes getQuotes(int instrumentId) {
		Quotes quotes = INSTANCE.quotesMap.get(instrumentId);
		if (quotes == null) {
			quotes = Quotes.newLevel10();
			INSTANCE.quotesMap.put(instrumentId, quotes);
		}
		return quotes;
	}

	public static void updateQuotes(int instrumentId, QuotesUpdater updater) {
		updater.update(getQuotes(instrumentId));
	}

	@FunctionalInterface
	public interface QuotesUpdater extends Consumer<Quotes> {
		default void update(Quotes quotes) {
			accept(quotes);
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
