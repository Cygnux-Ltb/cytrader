package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.ActChildOrder;
import io.mercury.redstone.core.order.Order;

public final class FtdcInputOrderActionConverter implements Function<Order, CThostFtdcInputOrderActionField>{

	@Override
	public CThostFtdcInputOrderActionField apply(Order order) {
		ActChildOrder childOrder = (ActChildOrder) order;
		Instrument instrument = order.instrument();
		CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();
		
		
		
		
		
		
		
		
		return inputOrderActionField;
	}

}
