package io.ffreedom.redstone.core.adaptor;

import java.util.Collection;

import io.ffreedom.redstone.core.adaptor.dto.QueryPositions;
import io.ffreedom.redstone.core.order.Order;

public interface OutboundAdaptor extends Adaptor {

	boolean newOredr(Order order);

	boolean cancelOrder(Order order);

	default boolean modifyOrder(Order order) {
		throw new AdaptorMethodNotImplementedException("AdaptorId -> " + getAdaptorId() + ", AdaptorName - >"
				+ getAdaptorName() + " - Method modifyOrder(order) is not implemented.");
	}

	Collection<Order> queryPositions(QueryPositions queryPositions);

}
