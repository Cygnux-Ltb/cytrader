package io.cygnux.repository.dao;

import io.cygnux.repository.entities.StBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarDao extends JpaRepository<StBar, Long> {

	@Query("SELECT '*' FROM #{#entityName} e "
			+ " WHERE " 
			+ " e.instrumentCode LIKE :instrumentCode% " 
			+ " AND "
			+ " e.tradingDay = :tradingDay ")
	List<StBar> query(
			@Param("instrumentCode") String instrumentCode, 
			@Param("tradingDay") int tradingDay);

}
