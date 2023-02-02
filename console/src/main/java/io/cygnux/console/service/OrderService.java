package io.cygnux.console.service;

import io.cygnux.console.persistence.dao.OrderDao;
import io.cygnux.console.persistence.dao.OrderEventDao;
import io.cygnux.console.persistence.util.DaoExecutor;
import io.cygnux.console.persistence.entity.OrderEntity;
import io.cygnux.console.persistence.entity.OrderEventEntity;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnux.console.controller.util.RequestUtil.checkInstrumentCode;
import static io.cygnux.console.controller.util.RequestUtil.checkInvestorId;
import static io.cygnux.console.controller.util.RequestUtil.checkOrdSysId;
import static io.cygnux.console.controller.util.RequestUtil.checkStrategyId;
import static io.cygnux.console.controller.util.RequestUtil.checkTradingDay;
import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

@Service
public class OrderService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderDao dao;

    @Resource
    private OrderEventDao eventDao;

    /**
     * @param strategyId     int
     * @param investorId     String
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<TOrder>
     */
    public List<OrderEntity> getOrders(int strategyId, String investorId, String instrumentCode, int tradingDay) {
        return getOrders(strategyId, investorId, instrumentCode, tradingDay, tradingDay);
    }

    /**
     * @param strategyId      int
     * @param investorId      String
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<TOrder>
     */
    public List<OrderEntity> getOrders(int strategyId, String investorId, String instrumentCode,
                                       int startTradingDay, int endTradingDay) {
        var errMsg = "[OrderService::getOrders] param error";
        if (checkStrategyId(strategyId, log, errMsg))
            Throws.illegalArgument("strategyId");
        if (checkTradingDay(startTradingDay, endTradingDay, log, errMsg))
            Throws.illegalArgument("startTradingDay & endTradingDay");
        if (checkInvestorId(investorId, log, errMsg))
            Throws.illegalArgument("investorId");
        if (checkInstrumentCode(instrumentCode, log, errMsg))
            Throws.illegalArgument("instrumentCode");
        return DaoExecutor.select(() -> dao.queryBy(strategyId, investorId, instrumentCode, startTradingDay, endTradingDay),
                OrderEntity.class);

    }

    /**
     * @param ordSysId long
     * @return List<TOrderEvent>
     */
    public List<OrderEventEntity> getOrderEventsByOrderSysId(long ordSysId) {
        if (checkOrdSysId(ordSysId, log, "[OrderService::getOrderEventsByOrderSysId] param error"))
            return new FastList<>();
        return exec(() -> eventDao.queryByOrdSysId(ordSysId), list -> {
            log.info("[OrderEventDao::queryByOrdSysId] return {} row, ordSysId=={}", list.size(), ordSysId);
            return list;
        }, e -> log.error("[OrderEventDao::queryByOrdSysId] exception, ordSysId=={}", ordSysId, e));
    }

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    public List<OrderEventEntity> getOrderEventsByTradingDay(int tradingDay) {
        if (checkTradingDay(tradingDay, log, "[OrderService::getOrderEventsByTradingDay] param error"))
            return new FastList<>();
        return exec(() -> eventDao.queryByTradingDay(tradingDay), list -> {
            log.info("[OrderEventDao::queryByTradingDay] return {} row, tradingDay=={}, ", list.size(), tradingDay);
            return list;
        }, e -> log.error("[OrderEventDao::queryByTradingDay] exception, tradingDay=={}", tradingDay, e));
    }

    /**
     * @param entity
     * @return
     */
    public boolean putOrder(OrderEntity entity) {
        return execBool(() -> dao.save(entity), o -> {
            log.info("[OrderDao::save] success -> {}", entity);
            return true;
        }, e -> {
            log.error("[OrderDao::save] failure -> {}", entity, e);
            return false;
        });
    }

    /**
     * @param entity
     * @return
     */
    public boolean putOrderEvent(OrderEventEntity entity) {
        return execBool(() -> eventDao.save(entity), o -> {
            log.info("[OrderEventDao::save] success -> {}", entity);
            return true;
        }, e -> {
            log.error("[OrderEventDao::save] failure -> {}", entity, e);
            return false;
        });
    }

}
