package io.cygnux.repository.dao;

import io.cygnux.repository.entities.TStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<TStrategy, Integer> {

    TStrategy queryByStrategyId(int strategyId);

    List<TStrategy> queryByStrategyName(String strategyName);

}
