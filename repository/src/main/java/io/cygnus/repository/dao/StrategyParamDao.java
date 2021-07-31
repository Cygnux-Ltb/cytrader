package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.StrategyParamEntity;

/**
 * StrategyParam DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface StrategyParamDao extends JpaRepository<StrategyParamEntity, Long> {

	List<StrategyParamEntity> queryByStrategyId(int strategyId);

	List<StrategyParamEntity> queryByStrategyName(String strategyName);

}
