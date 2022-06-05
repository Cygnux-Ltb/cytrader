package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.CygStrategyParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StrategyParam DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface StrategyParamDao extends JpaRepository<CygStrategyParam, Long> {

	List<CygStrategyParam> queryByStrategyId(int strategyId);

	List<CygStrategyParam> queryByStrategyName(String strategyName);

}
