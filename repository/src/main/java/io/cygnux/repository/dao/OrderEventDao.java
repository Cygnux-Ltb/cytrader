package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.CygOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderEvent DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface OrderEventDao extends JpaRepository<CygOrderEvent, Long> {

	List<CygOrderEvent> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

	List<CygOrderEvent> queryByTradingDay(int tradingDay);

	List<CygOrderEvent> queryByOrdSysId(long ordSysId);

}
