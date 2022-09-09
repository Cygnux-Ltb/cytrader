package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderEvent DAO
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<OrderEventEntity, Long> {

    List<OrderEventEntity> queryByStrategyIdAndTradingDay(int strategyId,
                                                          int tradingDay);

    List<OrderEventEntity> queryByTradingDay(int tradingDay);

    List<OrderEventEntity> queryByOrdSysId(long ordSysId);

}