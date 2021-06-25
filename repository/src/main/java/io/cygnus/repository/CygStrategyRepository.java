package io.cygnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygStrategy;

/**
 * 
 * @author yellow013
 * 
 *         CygStrategyRepository
 *
 */

@Repository
public interface CygStrategyRepository extends JpaRepository<CygStrategy, Long> {

}
