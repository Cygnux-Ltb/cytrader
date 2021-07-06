package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.InstrumentSettlementEntity;

/**
 * 
 * InstrumentSettlement DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface InstrumentSettlementDao extends JpaRepository<InstrumentSettlementEntity, Long> {

}
