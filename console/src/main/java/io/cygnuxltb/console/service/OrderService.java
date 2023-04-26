package io.cygnuxltb.console.service;

import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.dao.OrderDao;
import io.cygnuxltb.console.persistence.dao.OrderEventDao;
import io.cygnuxltb.console.persistence.entity.OrderEntity;
import io.cygnuxltb.console.persistence.entity.OrderEventEntity;
import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
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
import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

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
    public List<OrderEntity> getOrders(int strategyId,
                                       String investorId,
                                       String instrumentCode,
                                       int tradingDay) {
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
    public List<OrderEntity> getOrders(int strategyId,
                                       String investorId,
                                       String instrumentCode,
                                       int startTradingDay,
                                       int endTradingDay) {
        String errMsg = "[OrderService::getOrders] param error";
        if (illegalStrategyId(strategyId, log))
            Throws.illegalArgument("strategyId");
        if (illegalTradingDay(startTradingDay, endTradingDay, log))
            Throws.illegalArgument("startTradingDay & endTradingDay");
        if (illegalInvestorId(investorId, log))
            Throws.illegalArgument("investorId");
        if (illegalInstrumentCode(instrumentCode, log))
            Throws.illegalArgument("instrumentCode");
        return select(OrderEntity.class,
                () -> dao.queryBy(strategyId, investorId, instrumentCode,
                        startTradingDay, endTradingDay));
    }

    /**
     * @param ordSysId long
     * @return List<TOrderEvent>
     */
    public List<OrderEventEntity> getOrderEventsByOrderSysId(long ordSysId) {
        if (illegalOrdSysId(ordSysId, log))
            return new FastList<>();
        return select(OrderEventEntity.class,
                () -> eventDao.queryByOrdSysId(ordSysId));
    }

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    public List<OrderEventEntity> getOrderEventsByTradingDay(int tradingDay) {
        if (ControllerUtil.illegalTradingDay(tradingDay, log))
            return new FastList<>();
        return select(OrderEventEntity.class,
                () -> eventDao.queryByTradingDay(tradingDay));
    }

    /**
     * @param entity OrderEntity
     * @return boolean
     */
    public boolean putOrder(OrderEntity entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity OrderEventEntity
     * @return boolean
     */
    public boolean putOrderEvent(OrderEventEntity entity) {
        return insertOrUpdate(eventDao, entity);
    }

}
