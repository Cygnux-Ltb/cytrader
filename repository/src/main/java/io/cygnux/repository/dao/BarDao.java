package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entity.CygBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BarDao extends JpaRepository<CygBar, Long> {

	@Query("SELECT * FROM #{#entityName} e "
			+ " WHERE " 
			+ " e.instrumentCode LIKE :instrumentCode% " 
			+ " AND "
			+ " e.tradingDay = :tradingDay ")
	List<CygBar> query(
			@Param("instrumentCode") String instrumentCode, 
			@Param("tradingDay") int tradingDay);

}
