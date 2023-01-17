package io.cygnux.console.persistence.dao;

import io.cygnux.console.persistence.entity.PnlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlDao extends JpaRepository<PnlEntity, Long> {

    List<PnlEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);


}
