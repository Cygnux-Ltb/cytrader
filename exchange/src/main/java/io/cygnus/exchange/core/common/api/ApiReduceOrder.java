package io.cygnus.exchange.core.common.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ApiReduceOrder extends ApiCommand {

    public final long orderId;

    public final long uid;
    public final int symbol;
    public final long reduceSize;

    @Override
    public String toString() {
        return "[REDUCE " + orderId + " by " + reduceSize + "]";
    }
}
