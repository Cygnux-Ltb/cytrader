package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.CygPnlDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * PnlDaily DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface PnlDailyDao extends JpaRepository<CygPnlDaily, Long> {

	List<CygPnlDaily> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
