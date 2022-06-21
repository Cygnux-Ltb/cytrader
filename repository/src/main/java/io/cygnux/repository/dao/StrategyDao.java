package io.cygnux.repository.dao;

import io.cygnux.repository.entities.internal.InStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<InStrategy, Integer> {

    InStrategy queryByStrategyId(int strayegyId);

    List<InStrategy> queryByStrategyName(String strayegyName);

}
