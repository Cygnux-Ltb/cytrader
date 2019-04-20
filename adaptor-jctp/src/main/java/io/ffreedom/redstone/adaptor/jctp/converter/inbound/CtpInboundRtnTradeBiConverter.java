package io.ffreedom.redstone.adaptor.jctp.converter.inbound;

import io.ffreedom.common.functional.BiConverter;
import io.ffreedom.jctp.bean.rsp.RtnTrade;
import io.ffreedom.redstone.core.order.OrderReport;

public class CtpInboundRtnTradeBiConverter implements BiConverter<RtnTrade, OrderReport> {

	@Override
	public OrderReport convertTo(RtnTrade from, OrderReport to) {
		return to;
	}

}
