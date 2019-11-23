package io.redstone.adaptor.jctp.converter.outbound;

import ctp.thostapi.CThostFtdcInputOrderField;
import io.ffreedom.common.functional.Converter;
import io.redstone.actor.AppGlobalStatus;
import io.redstone.adaptor.jctp.utils.JctpOrderRefGenerate;
import io.redstone.core.order.api.Order;

public class CtpOutboundNewOrderConverter implements Converter<Order, CThostFtdcInputOrderField> {

	@Override
	public CThostFtdcInputOrderField convert(Order order) {
		int orderRef = JctpOrderRefGenerate.next(AppGlobalStatus.appId());
		char direction;
		switch (order.getSide().direction()) {
		case Long:
			direction = 0;
			break;
		case Short:
			direction = 1;
			break;
		default:
			throw new RuntimeException(order.getSide() + " does not exist.");
		}
		CThostFtdcInputOrderField inputOrderField = new CThostFtdcInputOrderField();
		inputOrderField.setInstrumentID(order.getInstrument().getInstrumentCode());
		inputOrderField.setOrderRef(Integer.toString(orderRef));
		inputOrderField.setDirection(direction);
		inputOrderField.setLimitPrice(order.getQtyPrice().getOfferPrice());
		inputOrderField.setVolumeTotalOriginal(Double.valueOf(order.getQtyPrice().getOfferQty()).intValue());
		return inputOrderField;
	}

}
