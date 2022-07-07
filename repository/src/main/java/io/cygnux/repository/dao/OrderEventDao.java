package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.ItOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderEvent DAO
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<ItOrderEvent, Long> {

    List<ItOrderEvent> queryByStrategyIdAndTradingDay(int strategyId,
                                                      int tradingDay);

    List<ItOrderEvent> queryByTradingDay(int tradingDay);

    List<ItOrderEvent> queryByOrdSysId(long ordSysId);

}
