package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygPnlDaily;

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
