package io.ffreedom.redstone.core.adaptor;

import io.ffreedom.redstone.core.adaptor.req.ReqQueryBalance;
import io.ffreedom.redstone.core.adaptor.req.ReqQueryPositions;
import io.ffreedom.redstone.core.adaptor.req.ReqSubscribeMarketData;
import io.ffreedom.redstone.core.assets.Balance;
import io.ffreedom.redstone.core.assets.Positions;
import io.ffreedom.redstone.core.order.Order;

public interface OutboundAdaptor<RSM extends ReqSubscribeMarketData, RQP extends ReqQueryPositions, RQB extends ReqQueryBalance>
		extends Adaptor {

	boolean subscribeMarketData(RSM subscribeMarketData);

	boolean newOredr(Order order);

	boolean cancelOrder(Order order);

	default boolean modifyOrder(Order order) {
		return false;
	}

	Positions queryPositions(RQP queryPositions);

	Balance queryBalance(RQB queryPositions);

}
