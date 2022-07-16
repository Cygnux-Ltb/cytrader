package io.cygnux.repository.dao;

import io.cygnux.repository.entities.TParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StrategyParam DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface ParamDao extends JpaRepository<TParam, Long> {

	List<TParam> queryByStrategyId(int strategyId);

	List<TParam> queryByStrategyName(String strategyName);

}
