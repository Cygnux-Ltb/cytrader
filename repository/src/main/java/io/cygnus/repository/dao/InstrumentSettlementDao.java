package io.cygnus.repository.dao;

import java.util.List;

import javax.annotation.Nullable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	@Query("SELECT * FROM #{#entityName} e " 
			+ " WHERE "
			+ " e.instrumentCode LIKE %:instrumentCode% " 
			+ " AND "
			+ " e.tradingDay = :tradingDay ")
	List<InstrumentSettlementEntity> query(@Nullable String instrumentCode, int tradingDay);
	
}
