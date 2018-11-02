package io.ffreedom.redstone.adaptor.ctp.setter;

import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundRtnTrade;
import io.ffreedom.redstone.core.order.Order;

public class CtpInboundRtnTradeSetter implements BeanSetter<CtpInboundRtnTrade, Order> {

	@Override
	public Order setBean(CtpInboundRtnTrade rtnOrder, Order order) {
		// TODO
		return order;
	}

}
