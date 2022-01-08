package io.cygnus.engine.trader;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.order.attr.OrdQty;
import io.horizon.trader.order.enums.OrdStatus;
import io.horizon.trader.transport.outbound.OrderReport;
import io.mercury.common.log.Log4j2LoggerFactory;

public final class OrderUpdater {

	/**
	 * Logger
	 */
	private static final Logger log = Log4j2LoggerFactory.getLogger(OrderUpdater.class);

	/**
	 * 根据订单回报处理订单状态
	 * 
	 * @param order
	 * @param report
	 */
	public static void updateWithReport(@Nonnull ChildOrder order, @Nonnull OrderReport report) {
		OrdQty qty = order.getQty();
		int filledQty = report.getFilledQty();
		OrdStatus status = OrdStatus.valueOf(report.getStatus());
		log.info("OrdReport status==[{}], filledQty==[{}], tradePrice==[{}], order.qty() -> {}",
				status, filledQty, report.getTradePrice(), qty);
		// 处理未返回订单状态的情况, 根据成交数量判断
		if (status == OrdStatus.Unprovided) {
			int offerQty = qty.getOfferQty();
			order.setStatus(
					// 成交数量等于委托数量, 状态为全部成交
					filledQty == offerQty ? OrdStatus.Filled
							// 成交数量小于委托数量同时成交数量大于0, 状态为部分成交
							: filledQty < offerQty && filledQty > 0 ? OrdStatus.PartiallyFilled
									// 成交数量等于0, 状态为New
									: OrdStatus.New);
		}
		// 已返回订单状态, 直接读取
		else {
			order.setStatus(status);
		}
		switch (order.getStatus()) {
		case PartiallyFilled:
			// 处理部分成交, 设置已成交数量
			// Set FilledQty
			order.getQty().setFilledQty(filledQty);
			// 新增订单成交记录
			// Add NewTrade record
			order.addRecord(report.getEpochMicros(), report.getTradePrice(),
					filledQty - order.getQty().getLastFilledQty());
			log.info(
					"ChildOrder current status PartiallyFilled, strategyId==[{}], ordSysId==[{}], "
							+ "filledQty==[{}], avgTradePrice==[{}]",
					order.getStrategyId(), order.getOrdSysId(), order.getQty().getFilledQty());
			break;
		case Filled:
			// 处理全部成交, 设置已成交数量
			// Set FilledQty
			order.getQty().setFilledQty(filledQty);
			// 新增订单成交记录
			// Add NewTrade Record
			order.addRecord(report.getEpochMicros(), report.getTradePrice(),
					filledQty - order.getQty().getLastFilledQty());
			// 计算此订单成交均价
			// Calculation AvgPrice
			long avgTradePrice = order.fillAndGetAvgTradePrice();
			log.info(
					"ChildOrder current status Filled, strategyId==[{}], ordSysId==[{}], "
							+ "filledQty==[{}], avgTradePrice==[{}]",
					order.getStrategyId(), order.getOrdSysId(), order.getQty().getFilledQty(), avgTradePrice);
			break;
		default:
			// 记录其他情况, 打印详细信息
			log.warn("Order updateWithReport finish, switch in default, order status==[{}], "
					+ "order -> {}, report -> {}", order.getStatus(), order, report);
			break;
		}
	}

}
