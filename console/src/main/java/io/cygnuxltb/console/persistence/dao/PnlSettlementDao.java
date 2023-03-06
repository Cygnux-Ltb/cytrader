package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.PnlSettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlementDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementDao extends JpaRepository<PnlSettlementEntity, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    List<PnlSettlementEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
