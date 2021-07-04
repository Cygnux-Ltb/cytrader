package io.cygnus.repository.dao;

import io.cygnus.repository.entity.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.StrategyParamEntity;

import java.util.List;

/**
 * @author yellow013
 * <p>
 * StrategyParamDao
 */
@Repository
public interface StrategyParamDao extends JpaRepository<StrategyParamEntity, Long> {

    List<StrategyParamEntity> queryByStrategyId(int strategyId);

}
