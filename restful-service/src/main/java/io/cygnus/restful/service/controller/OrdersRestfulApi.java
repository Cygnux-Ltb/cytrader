package io.cygnus.restful.service.resources;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.persistence.entity.Order;
import io.cygnus.repository.service.OrderService;
import io.cygnus.restful.service.base.CygRestfulApi;

@RestController("/order")
public class OrdersRestfulApi extends CygRestfulApi {

	@Resource
	private OrderService orderService;
	/**
	 * 查询Order
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @param investorId
	 * @param instrumentCode
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> getOrder(@RequestParam("strategyId") int strategyId,
			@RequestParam("tradingDay") String tradingDay, @RequestParam("investorId") String investorId,
			@RequestParam("instrumentCode") String instrumentCode) {
		if (checkParamIsNull(strategyId, tradingDay, investorId, instrumentCode)) {
			return httpBadRequest();
		}
		Date dateTradingDay = null;
		if (tradingDay != null) {
			dateTradingDay = changeTradingDay(tradingDay);
			if (dateTradingDay == null) {
				return httpBadRequest();
			}
		}
		OrderService ordersDao = new OrderService();
		List<Order> orders = ordersDao.getOrders(strategyId, tradingDay, investorId, instrumentCode);
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
		OrderService ordersDao = new OrderService();
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
		OrderService ordersDao = new OrderService();
		if (ordersDao.addOrder(order)) {
			return httpOk();
		}
		return httpInternalServerError();
	}

}
