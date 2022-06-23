package io.cygnux.repository.dao;

import io.cygnux.repository.entities.ItStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<ItStrategy, Integer> {

    ItStrategy queryByStrategyId(int strategyId);

    List<ItStrategy> queryByStrategyName(String strategyName);

}
