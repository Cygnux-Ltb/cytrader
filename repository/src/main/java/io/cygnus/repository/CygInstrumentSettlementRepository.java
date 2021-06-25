package io.cygnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygInstrumentSettlement;

/**
 * 
 * @author yellow013
 *
 */

@Repository
public interface CygInstrumentSettlementRepository extends JpaRepository<CygInstrumentSettlement, Long> {

}
