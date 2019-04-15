package io.ffreedom.redstone.core.order.utils;

import java.time.Instant;
import java.time.ZonedDateTime;

import io.ffreedom.common.datetime.TimeZones;
import io.ffreedom.redstone.core.order.OrderReport;
import io.ffreedom.redstone.core.order.api.Order;

public class OrderOperator {

	// TODO Call this update order
	public static void update(Order order, OrderReport report) {
		order.setStatus(report.getOrdStatus());
		switch (order.getStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.getQtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade record
			order.getTradeSet().addNewTrade(
					ZonedDateTime.ofInstant(Instant.ofEpochMilli(report.getEpochMilliseconds()), TimeZones.SYS_DEFAULT),
					report.getExecutePrice(), report.getFilledQty() - order.getQtyPrice().getLastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.getQtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade Record
			order.getTradeSet().addNewTrade(
					ZonedDateTime.ofInstant(Instant.ofEpochMilli(report.getEpochMilliseconds()), TimeZones.SYS_DEFAULT),
					report.getExecutePrice(), report.getFilledQty() - order.getQtyPrice().getLastFilledQty());
			// Calculation AvgPrice
			order.getQtyPrice().calculationAvgPrice(order.getTradeSet());
			break;
		default:
			break;
		}
	}

}
