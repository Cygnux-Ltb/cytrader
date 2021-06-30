package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygInstrument;

/**
 * 
 * @author yellow013
 * 
 *         CygInstrumentRepository
 *
 */
@Repository
public interface CygInstrumentDao extends JpaRepository<CygInstrument, Long> {

}
