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
	public static void updateWithReport(@Nonnull ActChildOrder order, @Nonnull OrdReport report) {
		OrdQty ordQty = order.ordQty();
		int filledQty = report.getFilledQty();
		log.info("OrdReport ordStatus==[{}], filledQty()==[{}], tradePrice==[{}], ordQty -> {}", report.getOrdStatus(),
				report.getTradePrice(), filledQty, ordQty);
		if (report.getOrdStatus() == OrdStatus.NotProvided) {
			// 处理未返回订单状态的情况, 根据成交数量判断
			int offerQty = ordQty.offerQty();
			order.setOrdStatus(
					// 成交数量等于委托数量, 状态为全部成交
					filledQty == offerQty ? OrdStatus.Filled
							// 成交数量小于委托数量同时成交数量大于0, 状态为部分成交
							: filledQty < offerQty && filledQty > 0 ? OrdStatus.PartiallyFilled
									// 成交数量等于0, 状态为New
									: OrdStatus.New);
		} else {
			// 已返回订单状态, 直接读取
			order.setOrdStatus(report.getOrdStatus());
		}
		switch (order.ordStatus()) {
		case PartiallyFilled:
			// 处理部分成交, 设置已成交数量
			// Set FilledQty
			order.ordQty().setFilledQty(filledQty);
			// 新增订单成交记录
			// Add NewTrade record
			order.trdRecordList().add(report.getEpochMillis(), report.getTradePrice(),
					filledQty - order.ordQty().lastFilledQty());
			break;
		case Filled:
			// 处理全部成交, 设置已成交数量
			// Set FilledQty
			order.ordQty().setFilledQty(filledQty);
			// 新增订单成交记录
			// Add NewTrade Record
			order.trdRecordList().add(report.getEpochMillis(), report.getTradePrice(),
					filledQty - order.ordQty().lastFilledQty());
			// 计算此订单成交均价
			// Calculation AvgPrice
			order.ordPrice().calculateAvgPrice(order.trdRecordList());
			break;
		default:
			break;
		}
	}

}
