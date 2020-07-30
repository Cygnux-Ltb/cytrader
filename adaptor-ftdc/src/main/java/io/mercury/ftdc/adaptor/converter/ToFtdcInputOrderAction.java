package io.mercury.ftdc.adaptor.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.ftdc.adaptor.consts.FtdcActionFlag;
import io.mercury.redstone.core.order.Order;

public final class ToFtdcInputOrderAction implements Function<Order, CThostFtdcInputOrderActionField> {

	@Override
	public CThostFtdcInputOrderActionField apply(Order order) {
		Instrument instrument = order.instrument();
		CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();

		inputOrderActionField.setActionFlag(FtdcActionFlag.Delete);

		inputOrderActionField.setExchangeID(instrument.symbol().exchange().code());

		inputOrderActionField.setInstrumentID(instrument.code());

		PriceMultiplier multiplier = instrument.symbol().getPriceMultiplier();
		inputOrderActionField.setLimitPrice(multiplier.toDouble(order.ordPrice().offerPrice()));

		inputOrderActionField.setVolumeChange(order.ordQty().leavesQty());

		// TODO 补充完整

		return inputOrderActionField;
	}

}
