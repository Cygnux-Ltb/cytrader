package io.cygnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygPnlSettlementDaily;

@Repository
public interface CygPnlSettlementDailyRepository extends JpaRepository<CygPnlSettlementDaily, Long> {

}
