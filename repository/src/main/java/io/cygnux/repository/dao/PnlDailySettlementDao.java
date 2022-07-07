package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.StPnlSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PnlSettlementDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlDailySettlementDao extends JpaRepository<StPnlSettlement, Long> {

    List<StPnlSettlement> queryByStrategyIdAndTradingDay(int strategyId,
                                                         int tradingDay);

}
