package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.BarEntity;

@Repository
public interface BarDao extends JpaRepository<BarEntity, Long> {

	@Query("SELECT * FROM #{#entityName} e WHERE " 
			+ " e.instrumentCode LIKE %:instrumentCode% " 
			+ " AND "
			+ " e.tradingDay = :tradingDay ")
	List<BarEntity> query(
			@Param("instrumentCode") String instrumentCode, 
			@Param("tradingDay") int tradingDay);

}
