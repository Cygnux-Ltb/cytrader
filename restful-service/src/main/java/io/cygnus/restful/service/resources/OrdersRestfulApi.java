package io.cygnus.restful.service.resources;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.persistence.entity.Order;
import io.cygnus.persistence.service.OrderDao;
import io.cygnus.restful.service.base.CygRestfulApi;

@RestController("/order")
public class OrdersRestfulApi extends CygRestfulApi {

	/**
	 * 查询Order
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @param investorId
	 * @param instrumentId
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> getOrder(@RequestParam("strategyId") Integer strategyId,
			@RequestParam("tradingDay") String tradingDay, @RequestParam("investorId") String investorId,
			@RequestParam("instrumentId") String instrumentId) {
		if (checkParamIsNull(strategyId, tradingDay, investorId, instrumentId)) {
			return httpBadRequest();
		}
		Date dateTradingDay = null;
		if (tradingDay != null) {
			dateTradingDay = changeTradingDay(tradingDay);
			if (dateTradingDay == null) {
				return httpBadRequest();
			}
		}
		OrderDao ordersDao = new OrderDao();
		List<Order> orders = ordersDao.getOrders(strategyId, dateTradingDay, investorId, instrumentId);
		return jsonResponse(orders);
	}

	/**
	 * 
	 * @param tradingDay
	 * @param strategyId
	 * @return
	 */
	@GetMapping("/init")
	public ResponseEntity<Object> getOrdersByInit(@RequestParam("tradingDay") String tradingDay,
			@RequestParam("strategyId") Integer strategyId) {
		// TODO 过滤最后的订单
		if (checkParamIsNull(strategyId, tradingDay)) {
			return httpBadRequest();
		}
		Date dateTradingDay = null;
		if (tradingDay != null) {
			dateTradingDay = changeTradingDay(tradingDay);
			if (dateTradingDay == null) {
				return httpBadRequest();
			}
		}
		OrderDao ordersDao = new OrderDao();
		List<Order> orders = ordersDao.getOrdersByInit(dateTradingDay, strategyId);
		return jsonResponse(orders);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Object> putOrder(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		Order order = jsonToObj(json, Order.class);
		OrderDao ordersDao = new OrderDao();
		if (ordersDao.addOrder(order)) {
			return httpOk();
		}
		return httpInternalServerError();
	}

}
