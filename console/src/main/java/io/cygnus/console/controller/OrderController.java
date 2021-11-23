package io.cygnus.restful.service.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.CygOrder;
import io.cygnus.repository.entity.CygOrderEvent;
import io.cygnus.restful.service.OrderService;
import io.cygnus.restful.service.base.BaseController;

@RestController("/order")
public class OrderController extends BaseController {

	@Resource
	private OrderService service;

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
			@RequestParam("investorId") String investorId, @RequestParam("instrumentCode") String instrumentCode,
			@RequestParam("tradingDay") int tradingDay) {
		if (checkParamIsNull(strategyId, tradingDay, investorId, instrumentCode)) {
			return badRequest();
		}
		List<CygOrder> orders = service.getOrders(strategyId, investorId, instrumentCode, tradingDay);
		return responseOf(orders);
	}

	/**
	 * 
	 * @param tradingDay
	 * @param strategyId
	 * @return
	 */
	@GetMapping("/status")
	public ResponseEntity<Object> getOrdersByInit(@RequestParam("tradingDay") int tradingDay,
			@RequestParam("strategyId") int strategyId) {

		if (checkParamIsNull(strategyId, tradingDay)) {
			return badRequest();
		}
		List<CygOrderEvent> orderEvents = service.getOrderEvents(tradingDay);
		// TODO 过滤最后的订单
		return responseOf(orderEvents);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Object> putOrder(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		return service.putOrder(toObject(json, CygOrder.class)) ? ok() : internalServerError();
	}

}
