package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.PnlSettlementDailyEntity;

import java.util.List;

/**
 * @author yellow013
 * <p>
 * PnlSettlementDailyDao
 */
@Repository
public interface PnlSettlementDailyDao extends JpaRepository<PnlSettlementDailyEntity, Long> {

    List<PnlSettlementDailyEntity> queryByStrategyId(int strategyId);

}
