package io.ffreedom.redstone.adaptor.jctp.converter.inbound;

import io.ffreedom.common.functional.BiConverter;
import io.ffreedom.jctp.bean.rsp.RtnTrade;
import io.ffreedom.redstone.core.order.api.Order;

public class CtpInboundRtnTradeBiConverter implements BiConverter<RtnTrade, Order> {

	@Override
	public Order convertTo(RtnTrade from, Order to) {
		return to;
	}

}
