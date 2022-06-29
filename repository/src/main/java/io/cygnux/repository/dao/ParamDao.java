package io.cygnux.repository.dao;

import io.cygnux.repository.entities.ItParam;
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
public interface ParamDao extends JpaRepository<ItParam, Long> {

	List<ItParam> queryByStrategyId(int strategyId);

	List<ItParam> queryByStrategyName(String strategyName);

}
