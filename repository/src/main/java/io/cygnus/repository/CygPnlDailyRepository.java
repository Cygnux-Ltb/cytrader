package io.cygnus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygPnlDaily;

@Repository
public interface CygPnlDailyRepository extends JpaRepository<CygPnlDaily, Long> {

	@Query("")
	List<CygPnlDaily> queryPnlDailys(int strategyId, long tradingDay);

}
