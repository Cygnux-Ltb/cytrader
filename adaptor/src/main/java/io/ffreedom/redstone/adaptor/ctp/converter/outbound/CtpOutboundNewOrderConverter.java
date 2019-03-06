package io.ffreedom.redstone.adaptor.ctp.converter.outbound;

import ctp.thostapi.CThostFtdcInputOrderField;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.redstone.actor.AppGlobalStatus;
import io.ffreedom.redstone.adaptor.ctp.utils.CtpOrderRefGenerate;
import io.ffreedom.redstone.core.order.Order;

public class CtpOutboundNewOrderConverter implements Converter<Order, CThostFtdcInputOrderField> {

	@Override
	public CThostFtdcInputOrderField convert(Order order) {
		int orderRef = CtpOrderRefGenerate.next(AppGlobalStatus.appId());
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
