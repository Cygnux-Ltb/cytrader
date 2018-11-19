package io.ffreedom.redstone.core.adaptor;

import io.ffreedom.redstone.core.adaptor.req.ReqQueryBalance;
import io.ffreedom.redstone.core.adaptor.req.ReqQueryPositions;
import io.ffreedom.redstone.core.adaptor.req.ReqSubscribeMarketData;
import io.ffreedom.redstone.core.balance.Balance;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.position.Positions;

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
