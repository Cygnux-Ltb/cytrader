package io.redstone.adaptor.jctp.converter.outbound;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.ffreedom.common.functional.Converter;
import io.redstone.core.order.api.Order;

public class CtpOutboundCancelOrderConverter implements Converter<Order, CThostFtdcInputOrderActionField> {

	@Override
	public CThostFtdcInputOrderActionField convert(Order order) {

		CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();

		return inputOrderActionField;
	}

}
