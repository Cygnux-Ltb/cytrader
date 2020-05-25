package io.mercury.redstone.core.order;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.redstone.core.order.specific.ChildOrder;
import io.mercury.redstone.core.order.structure.OrdReport;

public final class OrderUpdater {

	private static final Logger log = CommonLoggerFactory.getLogger(OrderUpdater.class);

	/**
	 * 根据订单回报处理订单状态
	 * 
	 * @param order
	 * @param report
	 */
	public static void updateOrderWithReport(@Nonnull ChildOrder order, @Nonnull OrdReport report) {
		log.info("Update order with report -> {}", report);
		order.setOrdStatus(report.getOrdStatus());
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
