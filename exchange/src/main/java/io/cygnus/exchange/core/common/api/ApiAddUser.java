package io.cygnus.exchange.core.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public final class ApiAddUser extends ApiCommand {

	public final long uid;

	@Override
	public String toString() {
		return "[ADDUSER " + uid + "]";
	}
}
