package io.ffreedom.redstone.adaptor.ctp.converter.outbound;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.redstone.core.order.Order;

public class CtpOutboundCancelOrderConverter implements Converter<Order, CThostFtdcInputOrderActionField> {

	@Override
	public CThostFtdcInputOrderActionField convert(Order order) {

		CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();

		return inputOrderActionField;
	}

}
