package io.cygnus.exchange.core.common.api;

import io.cygnus.exchange.core.common.api.binary.BinaryDataCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public final class ApiBinaryDataCommand extends ApiCommand {

	// transfer unique id
	// can be constant unless going to push data concurrently
	public final int transferId;

	// serializable object
	public final BinaryDataCommand data;

	@Override
	public String toString() {
		return "[BINARY_DATA tid=" + transferId + " data=" + data + "]";
	}
}
