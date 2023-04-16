package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<StrategyEntity, Long> {

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    StrategyEntity queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    StrategyEntity queryByStrategyName(String strategyName);

}
