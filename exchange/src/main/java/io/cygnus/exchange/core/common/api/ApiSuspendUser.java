package io.cygnus.exchange.core.common.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
public final class ApiSuspendUser extends ApiCommand {

    public final long uid;

    @Override
    public String toString() {
        return "[SUSPEND_USER " + uid + "]";
    }
}
