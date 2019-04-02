package io.ffreedom.redstone.adaptor.jctp.converter.inbound;

import io.ffreedom.common.functional.BiConverter;
import io.ffreedom.jctp.bean.rsp.RtnOrder;
import io.ffreedom.redstone.core.order.Order;

public class CtpInboundRtnOrderBiConverter implements BiConverter<RtnOrder, Order> {

	@Override
	public Order convertTo(RtnOrder from, Order to) {
		// TODO Auto-generated method stub
		return to;
	}

}
