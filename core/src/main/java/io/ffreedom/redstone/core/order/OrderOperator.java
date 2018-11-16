package io.ffreedom.redstone.core.order;

import java.time.Instant;
import java.time.ZonedDateTime;

import io.ffreedom.common.datetime.TimeZones;

public class OrderOperator {

	public static void update(Order order, OrderReport report) {
		order.setStatus(report.getOrdStatus());
		switch (order.getStatus()) {
		case PartiallyFilled:
			// Set FilledQty
			order.getQtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade record
			order.getTradeList().addNewTrade(
					ZonedDateTime.ofInstant(Instant.ofEpochMilli(report.getEpochMilliseconds()), TimeZones.SYS_DEFAULT),
					report.getExecPrice(), report.getFilledQty() - order.getQtyPrice().getLastFilledQty());
			break;
		case Filled:
			// Set FilledQty
			order.getQtyPrice().setFilledQty(report.getFilledQty());
			// Add NewTrade Record
			order.getTradeList().addNewTrade(
					ZonedDateTime.ofInstant(Instant.ofEpochMilli(report.getEpochMilliseconds()), TimeZones.SYS_DEFAULT),
					report.getExecPrice(), report.getFilledQty() - order.getQtyPrice().getLastFilledQty());
			// Calculation AvgPrice
			order.getQtyPrice().calculationAvgPrice(order.getTradeList());
			break;
		default:
			break;
		}
	}

}
