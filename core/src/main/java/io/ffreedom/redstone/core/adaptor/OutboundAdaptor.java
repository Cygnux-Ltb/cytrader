package io.ffreedom.redstone.core.adaptor;

import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.adaptor.dto.ReplyBalance;
import io.ffreedom.redstone.core.adaptor.dto.ReplyPositions;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.order.Order;

public interface OutboundAdaptor<SMD extends SubscribeMarketData, RP extends ReplyPositions, RB extends ReplyBalance>
		extends Adaptor {

	boolean subscribeMarketData(SMD subscribeMarketData);

	boolean newOredr(Order order);

	boolean cancelOrder(Order order);

	default boolean modifyOrder(Order order) {
		return false;
	}

	RP queryPositions(Account account);

	RB queryBalance(Account account);

}
