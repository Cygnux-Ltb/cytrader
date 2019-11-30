package io.redstone.core.order.utils;

import io.redstone.core.order.impl.ChildOrder;
import io.redstone.core.order.impl.OrderReport;

public final class OrderOperator {

	// TODO Call this update order
	public static void update(ChildOrder order, OrderReport report) {
		order.status(report.getOrdStatus());
		switch (order.status()) {
		case PartiallyFilled:
			// Set FilledQty
			order.qtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade record
			order.getTradeSet().addNewTrade(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.qtyPrice().getLastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.qtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade Record
			order.getTradeSet().addNewTrade(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.qtyPrice().getLastFilledQty());
			// Calculation AvgPrice
			order.qtyPrice().calculationAvgPrice(order.getTradeSet());
			break;
		default:
			break;
		}
	}

}
