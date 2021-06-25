package io.cygnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygStrategyParam;

/**
 * 
 * @author yellow013
 * 
 *         CygStrategyParamRepository
 *
 */
@Repository
public interface CygStrategyParamRepository extends JpaRepository<CygStrategyParam, Long> {

}
