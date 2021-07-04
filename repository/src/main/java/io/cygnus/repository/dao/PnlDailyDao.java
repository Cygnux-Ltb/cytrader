package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.constant.TableName;
import io.cygnus.repository.entity.PnlDailyEntity;

/**
 * @author yellow013
 * <p>
 * PnlDailyDao
 */
@Repository
public interface PnlDailyDao extends JpaRepository<PnlDailyEntity, Long> {

    @Query("SELECT * FROM #{#" + TableName.CYG_INFO + "} where ")
    List<PnlDailyEntity> query(int strategyId, long tradingDay);

}
