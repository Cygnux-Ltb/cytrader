package io.cygnus.exchange.core.common.api;

import io.cygnus.exchange.core.common.enums.OrderAction;
import io.cygnus.exchange.core.common.enums.OrderType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ApiPlaceOrder extends ApiCommand {

    public final long price;
    public final long size;
    public final long orderId;
    public final OrderAction action;
    public final OrderType orderType;
    public final long uid;
    public final int symbol;
    public final int userCookie;
    public final long reservePrice;

    @Override
    public String toString() {
        return "[ADD o" + orderId + " s" + symbol + " u" + uid + " " + (action == OrderAction.ASK ? 'A' : 'B')
                + ":" + (orderType == OrderType.IOC ? "IOC" : "GTC")
                + ":" + price + ":" + size + "]";
        //(reservePrice != 0 ? ("(R" + reservePrice + ")") : "") +
    }
}
