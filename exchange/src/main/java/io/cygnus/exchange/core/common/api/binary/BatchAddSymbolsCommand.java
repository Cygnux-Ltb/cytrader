package io.cygnus.exchange.core.common.api.binary;

import io.cygnus.exchange.core.common.CoreSymbolSpecification;
import io.cygnus.exchange.core.utils.SerializationUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import java.util.Collection;

@EqualsAndHashCode
@AllArgsConstructor
public final class BatchAddSymbolsCommand implements BinaryDataCommand {

	@Getter
	private final MutableIntObjectMap<CoreSymbolSpecification> symbols;

	public BatchAddSymbolsCommand(final CoreSymbolSpecification symbol) {
		symbols = IntObjectHashMap.newWithKeysValues(symbol.symbolId, symbol);
	}

	public BatchAddSymbolsCommand(final Collection<CoreSymbolSpecification> collection) {
		symbols = new IntObjectHashMap<>(collection.size());
		collection.forEach(spec -> symbols.put(spec.symbolId, spec));
	}

	public BatchAddSymbolsCommand(final BytesIn<?> bytes) {
		symbols = SerializationUtils.readIntHashMap(bytes, CoreSymbolSpecification::new);
	}

	@Override
	public void writeMarshallable(@SuppressWarnings("rawtypes") BytesOut bytes) {
		SerializationUtils.marshallIntHashMap(symbols, bytes);
	}

	@Override
	public int getBinaryCommandTypeCode() {
		return BinaryCommandType.ADD_SYMBOLS.getCode();
	}
}
