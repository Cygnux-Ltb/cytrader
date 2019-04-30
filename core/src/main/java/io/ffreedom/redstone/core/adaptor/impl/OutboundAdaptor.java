package io.ffreedom.redstone.core.adaptor.impl;

import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.order.impl.ChildOrder;

public abstract class OutboundAdaptor extends AbstractAdaptor {

	public OutboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean subscribeMarketData(SubscribeMarketData subscribeMarketData);

	public abstract boolean newOredr(ChildOrder order);

	public abstract boolean cancelOrder(ChildOrder order);

	public abstract boolean queryPositions(Account account);

	public abstract boolean queryBalance(Account account);

	@Override
	public AdaptorType getAdaptorType() {
		return AdaptorType.Outbound;
	}

}
