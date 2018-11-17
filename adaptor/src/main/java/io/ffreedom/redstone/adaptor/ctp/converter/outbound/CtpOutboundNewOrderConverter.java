package io.ffreedom.redstone.adaptor.ctp.converter.outbound;

import io.ffreedom.common.functional.Converter;
import io.ffreedom.redstone.actor.AppGlobalStatus;
import io.ffreedom.redstone.adaptor.ctp.dto.outbound.CtpOutboundNewOrder;
import io.ffreedom.redstone.adaptor.ctp.utils.CtpOrderRefGenerate;
import io.ffreedom.redstone.core.order.Order;

public class CtpOutboundNewOrderConverter implements Converter<Order, CtpOutboundNewOrder> {

	@Override
	public CtpOutboundNewOrder convert(Order order) {

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
		CtpOutboundNewOrder ctpNewOrder = new CtpOutboundNewOrder(orderRef, order.getInstrument().getInstrumentCode(),
				order.getQtyPrice().getOfferPrice(), Double.valueOf(order.getQtyPrice().getOfferQty()).intValue(),
				direction);

		return ctpNewOrder;
	}

}
