package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.PnlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
