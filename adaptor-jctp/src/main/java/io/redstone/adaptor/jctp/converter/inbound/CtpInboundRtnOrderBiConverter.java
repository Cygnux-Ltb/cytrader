package io.redstone.adaptor.jctp.converter.inbound;

import io.ffreedom.common.functional.BiConverter;
import io.ffreedom.jctp.bean.rsp.RtnOrder;
import io.redstone.core.order.impl.OrderReport;

public class CtpInboundRtnOrderBiConverter implements BiConverter<RtnOrder, OrderReport> {

	@Override
	public OrderReport convertTo(RtnOrder from, OrderReport to) {
		// TODO Auto-generated method stub
		return to;
	}

}
