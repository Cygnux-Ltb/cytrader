package io.redstone.core.adaptor.base;

import io.redstone.core.account.InvestorAccount;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.order.impl.ChildOrder;

public abstract class OutboundAdaptor extends AbstractAdaptor {

	public OutboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean subscribeMarketData(SubscribeMarketData subscribeMarketData);

	public abstract boolean newOredr(ChildOrder order);

	public abstract boolean cancelOrder(ChildOrder order);

	public abstract boolean queryPositions(InvestorAccount account);

	public abstract boolean queryBalance(InvestorAccount account);

	@Override
	public AdaptorType adaptorType() {
		return AdaptorType.Outbound;
	}

}
