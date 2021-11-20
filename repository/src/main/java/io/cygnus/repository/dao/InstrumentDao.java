package io.cygnus.repository.dao;

import java.util.List;

import javax.annotation.Nullable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygInstrument;

/**
 * 
 * Instrument DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface InstrumentDao extends JpaRepository<CygInstrument, Long> {

	@Query("SELECT * FROM #{#entityName} e " 
			+ " WHERE "
			+ "e.instrumentCode LIKE :instrumentCode ")
	List<CygInstrument> query(@Nullable String instrumentCode);

}
