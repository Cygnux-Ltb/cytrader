package io.ffreedom.redstone.core.order.utils;

import io.ffreedom.redstone.core.order.impl.ChildOrder;
import io.ffreedom.redstone.core.order.impl.OrderReport;

public final class OrderOperator {

	// TODO Call this update order
	public static void update(ChildOrder order, OrderReport report) {
		order.setStatus(report.getOrdStatus());
		switch (order.getStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.getQtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade record
			order.getTradeSet().addNewTrade(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.getQtyPrice().getLastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.getQtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade Record
			order.getTradeSet().addNewTrade(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.getQtyPrice().getLastFilledQty());
			// Calculation AvgPrice
			order.getQtyPrice().calculationAvgPrice(order.getTradeSet());
			break;
		default:
			break;
		}
	}

}
