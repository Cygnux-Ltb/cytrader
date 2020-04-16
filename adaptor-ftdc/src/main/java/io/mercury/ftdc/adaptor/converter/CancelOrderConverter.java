package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.redstone.core.order.Order;

public final class CancelOrderConverter implements Function<Order, CThostFtdcInputOrderActionField>{

	@Override
	public CThostFtdcInputOrderActionField apply(Order order) {
		CThostFtdcInputOrderActionField ftdcInputOrderAction = new CThostFtdcInputOrderActionField();

		return ftdcInputOrderAction;
	}

}
