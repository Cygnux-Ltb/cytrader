package io.cygnus.exchange.core.common;

import io.cygnus.exchange.core.common.enums.OrderAction;

public interface IOrder extends StateHash {

    long getPrice();

    long getSize();

    long getFilled();

    long getUid();

    OrderAction getAction();

    long getOrderId();

    long getTimestamp();

    long getReserveBidPrice();

}
