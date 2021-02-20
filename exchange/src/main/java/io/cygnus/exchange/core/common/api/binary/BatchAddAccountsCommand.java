package io.cygnus.exchange.core.common.api.binary;

import org.eclipse.collections.api.map.primitive.MutableIntLongMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.cygnus.exchange.core.utils.SerializationUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;

@EqualsAndHashCode
@AllArgsConstructor
public final class BatchAddAccountsCommand implements BinaryDataCommand {

	@Getter
	private final MutableLongObjectMap<MutableIntLongMap> users;

	public BatchAddAccountsCommand(final BytesIn<?> bytes) {
		users = SerializationUtils.readLongHashMap(bytes, c -> SerializationUtils.readIntLongHashMap(bytes));
	}

	@Override
	public void writeMarshallable(@SuppressWarnings("rawtypes") BytesOut bytes) {
		SerializationUtils.marshallLongHashMap(users, SerializationUtils::marshallIntLongHashMap, bytes);
	}

	@Override
	public int getBinaryCommandTypeCode() {
		return BinaryCommandType.ADD_ACCOUNTS.getCode();
	}
	
}
