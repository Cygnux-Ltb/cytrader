package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.internal.InOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderEvent DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface OrderEventDao extends JpaRepository<InOrderEvent, Long> {

	List<InOrderEvent> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

	List<InOrderEvent> queryByTradingDay(int tradingDay);

	List<InOrderEvent> queryByOrdSysId(long ordSysId);

}
