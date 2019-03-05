package io.ffreedom.redstone.core.adaptor;

import io.ffreedom.common.fsm.Enable;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.adaptor.dto.ReplyBalance;
import io.ffreedom.redstone.core.adaptor.dto.ReplyPositions;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.order.Order;

public abstract class OutboundAdaptor<SMD extends SubscribeMarketData, RP extends ReplyPositions, RB extends ReplyBalance>
		extends AbstractAdaptor implements Enable {

	public OutboundAdaptor(int adaptorId, String adaptorName) {
		super(adaptorId, adaptorName);
	}

	public abstract boolean subscribeMarketData(SMD subscribeMarketData);

	public abstract boolean newOredr(Order order);

	public abstract boolean cancelOrder(Order order);

	public boolean modifyOrder(Order order) {

		return false;
	}

	public abstract RP queryPositions(Account account);

	public abstract RB queryBalance(Account account);

	private boolean isEnabled;

	@Override
	public void enable() {
		this.isEnabled = true;
	}

	@Override
	public void disable() {
		this.isEnabled = false;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public boolean isDisabled() {
		return !isEnabled;
	}

	@Override
	public AdaptorType getAdaptorType() {
		return AdaptorType.Outbound;
	}

}
