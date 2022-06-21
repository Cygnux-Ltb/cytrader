package io.cygnux.console.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.cygnux.console.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.repository.entities.internal.InOrder;
import io.cygnux.repository.entities.internal.InOrderEvent;

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
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getOrder(@RequestParam("strategyId") int strategyId,
                                           @RequestParam("investorId") String investorId, @RequestParam("instrumentCode") String instrumentCode,
                                           @RequestParam("tradingDay") int tradingDay) {
        if (checkParamIsNull(strategyId, tradingDay, investorId, instrumentCode)) {
            return badRequest();
        }
        var orders = service.getOrders(strategyId, investorId, instrumentCode, tradingDay);
        return responseOf(orders);
    }

    /**
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
        var events = service.getOrderEventsByTradingDay(tradingDay);
        // TODO 过滤最后的订单
        return responseOf(events);
    }

    /**
     * @param request
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> putOrder(@RequestBody HttpServletRequest request) {
        var order = bodyToObject(request, InOrder.class);
        return order == null ? badRequest() : service.putOrder(order) ? ok() : internalServerError();
    }

}
