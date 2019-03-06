package io.ffreedom.redstone.adaptor.ctp.setter;

import ctp.thostapi.CThostFtdcTradeField;
import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.redstone.core.order.Order;

public class CtpInboundRtnTradeSetter implements BeanSetter<CThostFtdcTradeField, Order> {

	@Override
	public Order setBean(CThostFtdcTradeField rtnOrder, Order order) {
		// TODO
		return order;
	}

}
