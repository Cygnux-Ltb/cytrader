package io.ffreedom.redstone.core.adaptor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.order.api.Order;

public abstract class OutboundAdaptor extends AbstractAdaptor {

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	public OutboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean subscribeMarketData(SubscribeMarketData subscribeMarketData);

	public abstract boolean newOredr(Order order);

	public abstract boolean cancelOrder(Order order);

	public abstract boolean queryPositions(Account account);

	public abstract boolean queryBalance(Account account);

	@Override
	public AdaptorType getAdaptorType() {
		return AdaptorType.Outbound;
	}

}
