package io.ffreedom.redstone.core.adaptor;

import java.util.Collection;

import io.ffreedom.redstone.core.adaptor.req.ReqQueryBalance;
import io.ffreedom.redstone.core.adaptor.req.ReqQueryPositions;
import io.ffreedom.redstone.core.adaptor.req.ReqSubscribeMarketData;
import io.ffreedom.redstone.core.order.Order;

public interface OutboundAdaptor<RSM extends ReqSubscribeMarketData, RQP extends ReqQueryPositions, RQB extends ReqQueryBalance>
		extends Adaptor {

	boolean subscribeMarketData(RSM subscribeMarketData);

	boolean newOredr(Order order);

	boolean cancelOrder(Order order);

	default boolean modifyOrder(Order order) {
		throw new UnsupportedOperationException("AdaptorId -> " + getAdaptorId() + ", AdaptorName - >"
				+ getAdaptorName() + " - Method modifyOrder(order) is unsupported.");
	}

	Collection<Order> queryPositions(RQP queryPositions);

	Collection<Order> queryBalance(RQB queryPositions);

}
