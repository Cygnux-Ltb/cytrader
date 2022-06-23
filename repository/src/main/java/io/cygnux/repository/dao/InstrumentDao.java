package io.cygnux.repository.dao;

import io.cygnux.repository.entities.StInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 
 * Instrument DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface InstrumentDao extends JpaRepository<StInstrument, Long> {

	@Query("SELECT '*' FROM #{#entityName} e "
			+ " WHERE "
			+ "e.instrumentCode LIKE :instrumentCode ")
	List<StInstrument> query(@Nullable String instrumentCode);

}
