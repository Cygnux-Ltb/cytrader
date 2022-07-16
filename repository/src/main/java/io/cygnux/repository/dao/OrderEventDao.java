package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.TOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderEvent DAO
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<TOrderEvent, Long> {

    List<TOrderEvent> queryByStrategyIdAndTradingDay(int strategyId,
                                                     int tradingDay);

    List<TOrderEvent> queryByTradingDay(int tradingDay);

    List<TOrderEvent> queryByOrdSysId(long ordSysId);

}
