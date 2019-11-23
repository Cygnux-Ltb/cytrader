package io.redstone.adaptor.jctp.converter.inbound;

import io.ffreedom.common.functional.BiConverter;
import io.ffreedom.jctp.bean.rsp.RtnTrade;
import io.redstone.core.order.impl.OrderReport;

public class CtpInboundRtnTradeBiConverter implements BiConverter<RtnTrade, OrderReport> {

	@Override
	public OrderReport convertTo(RtnTrade from, OrderReport to) {
		return to;
	}

}
