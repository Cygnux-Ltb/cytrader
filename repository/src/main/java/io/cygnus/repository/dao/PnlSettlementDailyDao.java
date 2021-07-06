package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.PnlSettlementDailyEntity;

import java.util.List;

/**
 * PnlSettlementDaily DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface PnlSettlementDailyDao extends JpaRepository<PnlSettlementDailyEntity, Long> {

	List<PnlSettlementDailyEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
