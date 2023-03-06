package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.persistence.entity.OrderEntity;
import io.cygnuxltb.console.service.OrderService;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping(path = "/order")
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
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/{tradingDay}")
    public ResponseEntity<?> getOrder(@PathVariable("tradingDay") int tradingDay,
                                      @RequestParam("strategyId") int strategyId,
                                      @RequestParam("investorId") String investorId,
                                      @RequestParam("instrumentCode") String instrumentCode) {
        if (RequestUtil.paramIsNull(strategyId, tradingDay, investorId, instrumentCode))
            return ResponseUtil.badRequest();
        var orders = service.getOrders(strategyId, investorId, instrumentCode, tradingDay);
        return ResponseUtil.responseOf(orders);
    }

    /**
     * @param tradingDay int
     * @param strategyId int
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/status")
    public ResponseEntity<Object> getOrdersByInit(@RequestParam("tradingDay") int tradingDay,
                                                  @RequestParam("strategyId") int strategyId) {
        if (RequestUtil.paramIsNull(strategyId, tradingDay)) {
            return ResponseUtil.badRequest();
        }
        var events = service.getOrderEventsByTradingDay(tradingDay);
        // TODO 过滤最后的订单
        return ResponseUtil.responseOf(events);
    }

    /**
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseEntity<Object> putOrder(@RequestBody HttpServletRequest request) {
        var order = RequestUtil.bodyToObject(request, OrderEntity.class);
        return order == null ? ResponseUtil.badRequest() : service.putOrder(order) ? ResponseUtil.ok() : ResponseUtil.internalServerError();
    }

}
