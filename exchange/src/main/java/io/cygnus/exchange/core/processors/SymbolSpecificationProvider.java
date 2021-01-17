package io.cygnus.exchange.core.processors;

import java.util.Objects;

import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.cygnus.exchange.core.common.CoreSymbolSpecification;
import io.cygnus.exchange.core.common.StateHash;
import io.cygnus.exchange.core.utils.HashingUtils;
import io.cygnus.exchange.core.utils.SerializationUtils;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;
import net.openhft.chronicle.bytes.WriteBytesMarshallable;

public final class SymbolSpecificationProvider implements WriteBytesMarshallable, StateHash {

	// symbol-> specs
	private final IntObjectHashMap<CoreSymbolSpecification> symbolSpecMap;

	public SymbolSpecificationProvider() {
		this.symbolSpecMap = new IntObjectHashMap<>();
	}

	public SymbolSpecificationProvider(BytesIn<?> bytes) {
		this.symbolSpecMap = SerializationUtils.readIntHashMap(bytes, CoreSymbolSpecification::new);
	}

	public boolean addSymbol(final CoreSymbolSpecification symbolSpecification) {
		if (getSymbolSpecification(symbolSpecification.symbolId) != null) {
			return false; // CommandResultCode.SYMBOL_MGMT_SYMBOL_ALREADY_EXISTS;
		} else {
			registerSymbol(symbolSpecification.symbolId, symbolSpecification);
			return true;
		}
	}

	/**
	 * Get symbol specification
	 *
	 * @param symbol - symbol code
	 * @return symbol specification
	 */
	public CoreSymbolSpecification getSymbolSpecification(int symbol) {
		return symbolSpecMap.get(symbol);
	}

	/**
	 * register new symbol specification
	 *
	 * @param symbol - symbol code
	 * @param spec   - symbol specification
	 */
	public void registerSymbol(int symbol, CoreSymbolSpecification spec) {
		symbolSpecMap.put(symbol, spec);
	}

	/**
	 * Reset state
	 */
	public void reset() {
		symbolSpecMap.clear();
	}

	@Override
	public void writeMarshallable(@SuppressWarnings("rawtypes") BytesOut bytes) {
		// write symbolSpecs
		SerializationUtils.marshallIntHashMap(symbolSpecMap, bytes);
	}

	@Override
	public int stateHash() {
		return Objects.hash(HashingUtils.stateHash(symbolSpecMap));
	}

}
