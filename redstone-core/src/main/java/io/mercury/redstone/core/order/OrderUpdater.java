package io.mercury.redstone.core.order;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.order.structure.OrdReport;

public final class OrderUpdater {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(OrderUpdater.class);

	/**
	 * 根据订单回报处理订单状态
	 * 
	 * @param order
	 * @param report
	 */
	public static void updateWithOrdReport(@Nonnull ActChildOrder order, @Nonnull OrdReport report) {
		OrdQty ordQty = order.ordQty();
		int filledQty = report.getFilledQty();
		log.info("OrdReport report.getOrdStatus==[{}], report.getFilledQty()==[{}], ordQty -> {}",
				report.getOrdStatus(), filledQty, ordQty);
		if (report.getOrdStatus() == OrdStatus.NotProvided) {
			int offerQty = ordQty.offerQty();
			order.setOrdStatus(
					// 成交数量等于委托数量, 状态为全部成交
					filledQty == offerQty ? OrdStatus.Filled
							// 成交数量小于委托数量同时成交数量大于0, 状态为部分成交
							: filledQty < offerQty && filledQty > 0 ? OrdStatus.PartiallyFilled
									// 成交数量等于0, 状态为New
									: OrdStatus.New);

		} else {
			order.setOrdStatus(report.getOrdStatus());
		}
		switch (order.ordStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.ordQty().setFilledQty(filledQty);
			// Add NewTrade record
			order.trdRecordList().add(report.getEpochMillis(), report.getTradePrice(),
					filledQty - order.ordQty().lastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.ordQty().setFilledQty(filledQty);
			// Add NewTrade Record
			order.trdRecordList().add(report.getEpochMillis(), report.getTradePrice(),
					filledQty - order.ordQty().lastFilledQty());
			// Calculation AvgPrice
			order.ordPrice().calculateAvgPrice(order.trdRecordList());
			break;
		default:
			break;
		}
	}

}
