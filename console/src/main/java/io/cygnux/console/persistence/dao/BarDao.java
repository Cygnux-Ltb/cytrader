package io.cygnux.console.persistence.dao;

import io.cygnux.console.persistence.entity.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Bar DAO
 *
 * @author yellow013
 */
@Repository
public interface BarDao extends JpaRepository<BarEntity, Long> {

    @Query("SELECT '*' FROM #{#entityName} e WHERE "
            + " e.instrumentCode LIKE :instrumentCode% "
            + " AND "
            + " e.tradingDay >= :startTradingDay "
            + " AND "
            + " e.tradingDay <= :endTradingDay ")
    List<BarEntity> query(@Param("instrumentCode") String instrumentCode,
                          @Param("startTradingDay") int startTradingDay,
                          @Param("endTradingDay") int endTradingDay);

}
