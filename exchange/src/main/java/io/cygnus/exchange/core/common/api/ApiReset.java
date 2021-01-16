package io.cygnus.exchange.core.common.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
public final class ApiReset extends ApiCommand {
	
    @Override
    public String toString() {
        return "[RESET]";
    }
    
}
