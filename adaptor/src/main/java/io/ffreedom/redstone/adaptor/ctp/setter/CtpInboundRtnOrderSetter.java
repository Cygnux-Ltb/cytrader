package io.ffreedom.redstone.adaptor.ctp.setter;

import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundRtnOrder;
import io.ffreedom.redstone.core.order.Order;

public class CtpInboundRtnOrderSetter implements BeanSetter<CtpInboundRtnOrder, Order> {

	@Override
	public Order setBean(CtpInboundRtnOrder rtnOrder, Order order) {
		// TODO
		return order;
	}

}
