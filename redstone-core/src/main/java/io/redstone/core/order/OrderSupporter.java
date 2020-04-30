package io.redstone.core.order;

import io.redstone.core.order.specific.ChildOrder;
import io.redstone.core.order.structure.OrdReport;

public final class OrderSupporter {

	// TODO Call this update order
	public static void updateOrderWithReport(ChildOrder order, OrdReport report) {
		order.updateOrdStatus(report.getOrdStatus());
		switch (order.ordStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.ordQty().filledQty(report.getFilledQty());
			// Add NewTrade record
			order.trdList().addNewRecord(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.ordQty().lastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.ordQty().filledQty(report.getFilledQty());
			// Add NewTrade Record
			order.trdList().addNewRecord(report.getEpochMillis(), report.getExecutePrice(),
					report.getFilledQty() - order.ordQty().lastFilledQty());
			// Calculation AvgPrice
			order.ordPrice().calculateAvgPrice(order.trdList());
			break;
		default:
			break;
		}
	}

}
