package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.OrderEntity;

/**
 * 
 * Order DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

	/**
	 * 
	 * @param strategyId
	 * @param startTradingDay
	 * @param endTradingDay
	 * @param investorId
	 * @param instrumentCode
	 * @return
	 */
	@Query("SELECT * FROM #{#entityName} e " 
			+ " WHERE e.strategyId = :strategyId "
			+ " AND e.tradingDay >= :startTradingDay " 
			+ " AND e.tradingDay <= :endTradingDay "
			+ " AND e.investorId LIKE :investorId " 
			+ " AND e.instrumentCode LIKE %:instrumentCode% ")
	List<OrderEntity> query(
			@Param("strategyId") int strategyId, 
			@Param("startTradingDay") int startTradingDay,
			@Param("endTradingDay") int endTradingDay, 
			@Param("investorId") String investorId,
			@Param("instrumentCode") String instrumentCode);

}
