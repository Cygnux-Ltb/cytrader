package io.cygnus.exchange.core.common.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
public final class ApiPersistState extends ApiCommand {

    public long dumpId;
    public boolean seal;

    @Override
    public String toString() {
        return "[PERSIST]-" + dumpId + " seal=" + seal;
    }
}
