package io.cygnux.console.controller;

import io.cygnux.console.service.OrderService;
import io.cygnux.console.persistence.entity.OrderEntity;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static io.cygnux.console.controller.util.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.controller.util.ParamsValidateUtil.paramIsNull;
import static io.cygnux.console.controller.util.ResponseUtil.badRequest;
import static io.cygnux.console.controller.util.ResponseUtil.internalServerError;
import static io.cygnux.console.controller.util.ResponseUtil.ok;
import static io.cygnux.console.controller.util.ResponseUtil.responseOf;

@RestController("/order")
public final class OrderController {

    @Resource
    private OrderService service;

    /**
     * 查询Order
     *
     * @param strategyId     int
     * @param tradingDay     String
     * @param investorId     String
     * @param instrumentCode int
     * @return ResponseEntity<Object>
     */
    @GetMapping
    public ResponseEntity<Object> getOrder(@RequestParam("strategyId") int strategyId,
                                           @RequestParam("investorId") String investorId,
                                           @RequestParam("instrumentCode") String instrumentCode,
                                           @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(strategyId, tradingDay, investorId, instrumentCode)) {
            return badRequest();
        }
        var orders = service.getOrders(strategyId, investorId, instrumentCode, tradingDay);
        return responseOf(orders);
    }

    /**
     * @param tradingDay int
     * @param strategyId int
     * @return ResponseEntity<Object>
     */
    @GetMapping("/status")
    public ResponseEntity<Object> getOrdersByInit(@RequestParam("tradingDay") int tradingDay,
                                                  @RequestParam("strategyId") int strategyId) {
        if (paramIsNull(strategyId, tradingDay)) {
            return badRequest();
        }
        var events = service.getOrderEventsByTradingDay(tradingDay);
        // TODO 过滤最后的订单
        return responseOf(events);
    }

    /**
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @PutMapping
    public ResponseEntity<Object> putOrder(@RequestBody HttpServletRequest request) {
        var order = bodyToObject(request, OrderEntity.class);
        return order == null ? badRequest() : service.putOrder(order) ? ok() : internalServerError();
    }

}
