package io.cygnux.repository.dao;

import java.util.List;

import io.cygnux.repository.entities.internal.InOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * Order DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface OrderDao extends JpaRepository<InOrder, Long> {

	/**
	 * 
	 * @param strategyId
	 * @param investorId
	 * @param instrumentCode
	 * @param startTradingDay
	 * @param endTradingDay
	 * @return
	 */
	@Query("SELECT * FROM #{#entityName} e WHERE "
			+ " e.strategyId = :strategyId "
			+ " AND "
			+ " e.investorId LIKE :investorId "
			+ " AND "
			+ " e.instrumentCode LIKE :instrumentCode% "
			+ " AND "
			+ " e.tradingDay >= :startTradingDay "
			+ " AND "
			+ " e.tradingDay <= :endTradingDay ")
	List<InOrder> query(
			@Param("strategyId") int strategyId, 
			@Param("investorId") String investorId,
			@Param("instrumentCode") String instrumentCode, 
			@Param("startTradingDay") int startTradingDay,
			@Param("endTradingDay") int endTradingDay);

}
