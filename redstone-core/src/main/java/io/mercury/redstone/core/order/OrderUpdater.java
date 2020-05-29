package io.mercury.redstone.core.order;

import javax.annotation.Nonnull;

import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.structure.OrdReport;

public final class OrderUpdater {

	/**
	 * 根据订单回报处理订单状态
	 * 
	 * @param order
	 * @param report
	 */
	public static void updateWithOrdReport(@Nonnull ActChildOrder order, @Nonnull OrdReport report) {
		if (report.getOrdStatus() == OrdStatus.NotProvided) {
			int filledQty = report.getFilledQty();
			// TODO
		} else {
			order.setOrdStatus(report.getOrdStatus());
		}
		switch (order.ordStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.ordQty().setFilledQty(report.getFilledQty());
			// Add NewTrade record
			order.trdRecordList().add(report.getEpochMillis(), report.getTradePrice(),
					report.getFilledQty() - order.ordQty().lastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.ordQty().setFilledQty(report.getFilledQty());
			// Add NewTrade Record
			order.trdRecordList().add(report.getEpochMillis(), report.getTradePrice(),
					report.getFilledQty() - order.ordQty().lastFilledQty());
			// Calculation AvgPrice
			order.ordPrice().calculateAvgPrice(order.trdRecordList());
			break;
		default:
			break;
		}
	}

}
