package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.CygStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * Strategy DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface StrategyDao extends JpaRepository<CygStrategy, Integer> {

	public CygStrategy queryByStrategyId(int strayegyId);

	public List<CygStrategy> queryByStrategyName(String strayegyName);

}
