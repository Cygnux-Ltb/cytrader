package io.ffreedom.redstone.adaptor.ctp.converter.outbound;

import io.ffreedom.common.functional.Converter;
import io.ffreedom.redstone.adaptor.ctp.dto.outbound.CtpOutboundCancelOrder;
import io.ffreedom.redstone.core.order.Order;

public class CtpOutboundCancelOrderConverter implements Converter<Order, CtpOutboundCancelOrder> {

	@Override
	public CtpOutboundCancelOrder convert(Order order) {

		return null;
	}

}
