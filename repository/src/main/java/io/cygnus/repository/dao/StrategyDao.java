package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygStrategy;

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
