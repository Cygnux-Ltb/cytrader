package io.cygnux.repository.dao;

import io.cygnux.repository.entity.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<StrategyEntity, Integer> {

    StrategyEntity queryByStrategyId(int strategyId);

    List<StrategyEntity> queryByStrategyName(String strategyName);

}
