package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygStrategy;

/**
 * @author yellow013
 * <p>
 * CygStrategyRepository
 */

@Repository
public interface CygStrategyDao extends JpaRepository<CygStrategy, Long> {

    CygStrategy queryStrategyByStrategyId(int strategyId);

}
