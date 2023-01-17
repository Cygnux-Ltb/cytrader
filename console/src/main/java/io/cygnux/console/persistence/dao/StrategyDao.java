package io.cygnux.console.dao;

import io.cygnux.console.entity.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<StrategyEntity, Long> {

    StrategyEntity queryByStrategyId(int strategyId);

    StrategyEntity queryByStrategyName(String strategyName);

}
