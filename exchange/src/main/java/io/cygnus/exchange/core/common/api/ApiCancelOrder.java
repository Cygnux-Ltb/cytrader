package io.cygnus.exchange.core.common.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ApiCancelOrder extends ApiCommand {

    public final long orderId;

    public final long uid;
    public final int symbol;

    @Override
    public String toString() {
        return "[CANCEL " + orderId + " u" + uid + " s" + symbol + "]";
    }
}
