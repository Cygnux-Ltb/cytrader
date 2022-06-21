package io.cygnux.repository.dao;

import java.util.List;

import javax.annotation.Nullable;

import io.cygnux.repository.entities.internal.InInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Instrument DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface InstrumentDao extends JpaRepository<InInstrument, Long> {

	@Query("SELECT * FROM #{#entityName} e " 
			+ " WHERE "
			+ "e.instrumentCode LIKE :instrumentCode ")
	List<InInstrument> query(@Nullable String instrumentCode);

}
