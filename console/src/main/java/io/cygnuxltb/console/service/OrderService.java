package io.cygnuxltb.console.service;

import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.dao.OrderDao;
import io.cygnuxltb.console.persistence.dao.OrderEventDao;
import io.cygnuxltb.console.persistence.entity.OrderEntity;
import io.cygnuxltb.console.persistence.entity.OrderEventEntity;
import io.cygnuxltb.console.persistence.util.DaoExecutor;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalInstrumentCode;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalInvestorId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalOrdSysId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalTradingDay;
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
        String errMsg = "[OrderService::getOrders] param error";
        if (illegalStrategyId(strategyId, log))
            Throws.illegalArgument("strategyId");
        if (illegalTradingDay(startTradingDay, endTradingDay, log))
            Throws.illegalArgument("startTradingDay & endTradingDay");
        if (illegalInvestorId(investorId, log))
            Throws.illegalArgument("investorId");
        if (illegalInstrumentCode(instrumentCode, log))
            Throws.illegalArgument("instrumentCode");
        return DaoExecutor.select(OrderEntity.class,
                () -> dao.queryBy(strategyId, investorId, instrumentCode, startTradingDay, endTradingDay));
    }

    /**
     * @param ordSysId long
     * @return List<TOrderEvent>
     */
    public List<OrderEventEntity> getOrderEventsByOrderSysId(long ordSysId) {
        if (illegalOrdSysId(ordSysId, log))
            return new FastList<>();
        return exec(() -> eventDao.queryByOrdSysId(ordSysId),
                list -> {
                    log.info("[OrderEventDao::queryByOrdSysId] return {} row, ordSysId=={}", list.size(), ordSysId);
                    return list;
                }, e -> log.error("[OrderEventDao::queryByOrdSysId] exception, ordSysId=={}", ordSysId, e));
    }

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    public List<OrderEventEntity> getOrderEventsByTradingDay(int tradingDay) {
        if (ControllerUtil.illegalTradingDay(tradingDay, log))
            return new FastList<>();
        return exec(() -> eventDao.queryByTradingDay(tradingDay),
                list -> {
                    log.info("[OrderEventDao::queryByTradingDay] return {} row, tradingDay=={}, ", list.size(), tradingDay);
                    return list;
                }, e -> log.error("[OrderEventDao::queryByTradingDay] exception, tradingDay=={}", tradingDay, e));
    }

    /**
     * @param entity OrderEntity
     * @return boolean
     */
    public boolean putOrder(OrderEntity entity) {
        return execBool(() -> dao.save(entity),
                o -> {
                    log.info("[OrderDao::save] success -> {}", entity);
                    return true;
                }, e -> {
                    log.error("[OrderDao::save] failure -> {}", entity, e);
                    return false;
                });
    }

    /**
     * @param entity OrderEventEntity
     * @return boolean
     */
    public boolean putOrderEvent(OrderEventEntity entity) {
        return execBool(() -> eventDao.save(entity),
                o -> {
                    log.info("[OrderEventDao::save] success -> {}", entity);
                    return true;
                }, e -> {
                    log.error("[OrderEventDao::save] failure -> {}", entity, e);
                    return false;
                });
    }

}
