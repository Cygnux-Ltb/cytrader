package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygPnlDailySettlement;

/**
 * PnlSettlementDaily DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface PnlDailySettlementDao extends JpaRepository<CygPnlDailySettlement, Long> {

	List<CygPnlDailySettlement> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
