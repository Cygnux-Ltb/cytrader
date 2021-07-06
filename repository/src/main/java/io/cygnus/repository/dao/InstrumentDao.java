package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.InstrumentEntity;

/**
 * 
 * Instrument DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface InstrumentDao extends JpaRepository<InstrumentEntity, Long> {

}
