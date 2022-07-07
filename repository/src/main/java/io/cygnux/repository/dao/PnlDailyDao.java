package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.StPnl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PnlDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlDailyDao extends JpaRepository<StPnl, Long> {

    List<StPnl> queryByStrategyIdAndTradingDay(int strategyId,
                                               int tradingDay);

}
