package io.cygnus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.constant.TableName;
import io.cygnus.repository.entity.CygPnlDaily;

/**
 * 
 * @author yellow013
 * 
 *         CygPnlDailyRepository
 *
 */
@Repository
public interface CygPnlDailyRepository extends JpaRepository<CygPnlDaily, Long> {

	@Query("SELECT * FROM " + TableName.CYG_INFO + "")
	List<CygPnlDaily> queryPnlDailys(int strategyId, long tradingDay);

}
