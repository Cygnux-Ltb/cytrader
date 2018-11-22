package io.ffreedom.redstone.core.adaptor;

import io.ffreedom.redstone.core.adaptor.dto.QueryBalance;
import io.ffreedom.redstone.core.adaptor.dto.QueryPositions;
import io.ffreedom.redstone.core.adaptor.dto.ReplyBalance;
import io.ffreedom.redstone.core.adaptor.dto.ReplyPositions;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.order.Order;

public interface OutboundAdaptor<SM extends SubscribeMarketData, QP extends QueryPositions, QB extends QueryBalance, RP extends ReplyPositions, RB extends ReplyBalance>
		extends Adaptor {

	boolean subscribeMarketData(SM subscribeMarketData);

	boolean newOredr(Order order);

	boolean cancelOrder(Order order);

	default boolean modifyOrder(Order order) {
		return false;
	}

	RP queryPositions(QP queryPositions);

	RB queryBalance(QB queryPositions);

}
