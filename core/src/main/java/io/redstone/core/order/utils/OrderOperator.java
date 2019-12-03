package io.redstone.core.order.utils;

import io.redstone.core.order.impl.ChildOrder;
import io.redstone.core.order.impl.OrderReport;

public final class OrderOperator {

	// TODO Call this update order
	public static void update(ChildOrder order, OrderReport report) {
		order.ordStatus(report.getOrdStatus());
		switch (order.ordStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.ordQty().filledQty(report.getFilledQty());
			// Add NewTrade record
			order.getTradeSet().addNewTrade(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.ordQty().lastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.ordQty().filledQty(report.getFilledQty());
			// Add NewTrade Record
			order.getTradeSet().addNewTrade(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.ordQty().lastFilledQty());
			// Calculation AvgPrice
			order.ordPrice().calculateAvgPrice(order.getTradeSet());
			break;
		default:
			break;
		}
	}

}
