package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.internal.InStrategyParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StrategyParam DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface StrategyParamDao extends JpaRepository<InStrategyParam, Long> {

	List<InStrategyParam> queryByStrategyId(int strategyId);

	List<InStrategyParam> queryByStrategyName(String strategyName);

}
