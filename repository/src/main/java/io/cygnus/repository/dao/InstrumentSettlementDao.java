package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.InstrumentSettlementEntity;

/**
 * @author yellow013
 * <p>
 * InstrumentSettlementDao
 */
@Repository
public interface InstrumentSettlementDao extends JpaRepository<InstrumentSettlementEntity, Long> {

}
