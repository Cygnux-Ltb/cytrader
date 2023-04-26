package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.OrderEntity;
import io.cygnuxltb.console.persistence.entity.OrderEventEntity;
import io.cygnuxltb.console.service.OrderService;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 订单服务接口
 */
@RestController
@RequestMapping(path = "/order", produces = MimeType.APPLICATION_JSON_UTF8)
public final class OrderController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService service;

    /**
     * 查询Order
     *
     * @param strategyId     int
     * @param tradingDay     String
     * @param investorId     String
     * @param instrumentCode int
     * @return List<OrderEntity>
     */
    @GetMapping(path = "/{tradingDay}")
    public List<OrderEntity> getOrder(@PathVariable("tradingDay") int tradingDay,
                                      @RequestParam("strategyId") int strategyId,
                                      @RequestParam("investorId") String investorId,
                                      @RequestParam("instrumentCode") String instrumentCode) {
        if (ControllerUtil.paramIsNull(strategyId, tradingDay, investorId, instrumentCode))
            return null;
        return service.getOrders(strategyId, investorId, instrumentCode, tradingDay);
    }

    /**
     * 获取订单最新状态
     *
     * @param tradingDay int
     * @param strategyId int
     * @return ResponseEntity<Object>
     */
    @GetMapping(path = "/status")
    public List<OrderEventEntity> getOrdersByInit(@RequestParam("tradingDay") int tradingDay,
                                                  @RequestParam("strategyId") int strategyId) {
        if (ControllerUtil.paramIsNull(strategyId, tradingDay)) {
            return null;
        }
        // TODO 过滤最后的订单
        return service.getOrderEventsByTradingDay(tradingDay);
    }

    /**
     * 新增订单
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus putOrder(@RequestBody HttpServletRequest request) {
        var order = ControllerUtil.bodyToObject(request, OrderEntity.class);
        return order == null
                ? ResponseStatus.BAD_REQUEST : service.putOrder(order)
                ? ResponseStatus.OK : ResponseStatus.INTERNAL_ERROR;
    }

}
