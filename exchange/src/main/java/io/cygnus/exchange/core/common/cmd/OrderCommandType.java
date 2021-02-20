package io.cygnus.exchange.core.common.cmd;

import org.eclipse.collections.api.map.primitive.ImmutableByteObjectMap;
import org.eclipse.collections.api.map.primitive.MutableByteObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.ByteObjectHashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderCommandType {

	PLACE_ORDER((byte) 1, true), //
	CANCEL_ORDER((byte) 2, true), //
	MOVE_ORDER((byte) 3, true), //
	REDUCE_ORDER((byte) 4, true), //

	ORDER_BOOK_REQUEST((byte) 6, false), //

	ADD_USER((byte) 10, true), //
	BALANCE_ADJUSTMENT((byte) 11, true), //
	SUSPEND_USER((byte) 12, true), //
	RESUME_USER((byte) 13, true), //

	BINARY_DATA_QUERY((byte) 90, false), //
	BINARY_DATA_COMMAND((byte) 91, true), //

	PERSIST_STATE_MATCHING((byte) 110, true), //
	PERSIST_STATE_RISK((byte) 111, true), //

	GROUPING_CONTROL((byte) 118, false), //
	NOP((byte) 120, false), //
	RESET((byte) 124, true), //
	SHUTDOWN_SIGNAL((byte) 127, false), //

	RESERVED_COMPRESSED((byte) -1, false),//

	;

	@Getter
	private final byte code;
	
	@Getter
	private final boolean mutate;

	public static OrderCommandType of(byte code) {
		// TODO try if-else
		final OrderCommandType result = CodesMap.get(code);
		if (result == null) {
			throw new IllegalArgumentException("Unknown order command type code:" + code);
		}
		return result;
	}

	private static final ImmutableByteObjectMap<OrderCommandType> CodesMap;

	static {
		MutableByteObjectMap<OrderCommandType> tempMap = new ByteObjectHashMap<>();
		for (OrderCommandType type : values()) {
			tempMap.put(type.code, type);
		}
		CodesMap = tempMap.toImmutable();
	}

}
