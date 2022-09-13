package io.cygnux.repository.dao;

import io.cygnux.repository.entity.PnlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlDailyDao extends JpaRepository<PnlEntity, Long> {

    List<PnlEntity> queryByStrategyIdAndTradingDay(int strategyId,
                                                   int tradingDay);

}
