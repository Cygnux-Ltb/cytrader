package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order DAO
 *
 * @author yellow013
 */
@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

    /**
     * @param strategyId      int
     * @param investorId      String
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<OrderEntity>
     */
    @Query("SELECT '*' FROM #{#entityName} e WHERE"
            + " e.strategyId = :strategyId "
            + " AND e.investorId = :investorId "
            + " AND e.instrumentCode = :instrumentCode "
            + " AND e.tradingDay >= :startTradingDay "
            + " AND e.tradingDay <= :endTradingDay ")
    List<OrderEntity> queryBy(@Param("strategyId") int strategyId,
                              @Param("investorId") String investorId,
                              @Param("instrumentCode") String instrumentCode,
                              @Param("startTradingDay") int startTradingDay,
                              @Param("endTradingDay") int endTradingDay);

    /**
     * @param ordSysId long
     * @return OrderEntity
     */
    OrderEntity queryByOrdSysId(long ordSysId);

}
