package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.StrategyEntity;

/**
 * @author yellow013
 * <p>
 * StrategyDao
 */

@Repository
public interface StrategyDao extends JpaRepository<StrategyEntity, Long> {

    StrategyEntity queryByStrategyId(int strategyId);

}
