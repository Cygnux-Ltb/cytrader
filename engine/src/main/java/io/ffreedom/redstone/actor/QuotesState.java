package io.ffreedom.redstone.actor;

import java.util.Optional;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

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

}
