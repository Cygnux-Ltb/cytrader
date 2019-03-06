package io.ffreedom.redstone.adaptor.ctp.setter;

import ctp.thostapi.CThostFtdcOrderField;
import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.redstone.core.order.Order;

public class CtpInboundRtnOrderSetter implements BeanSetter<CThostFtdcOrderField, Order> {

	@Override
	public Order setBean(CThostFtdcOrderField rtnOrder, Order order) {
		// TODO
		return order;
	}

}
