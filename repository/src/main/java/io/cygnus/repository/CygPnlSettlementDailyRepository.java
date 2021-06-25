package io.cygnus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.persistence.entity.CygPnlSettlementDaily;

@Repository
public interface CygPnlSettlementDailyRepository extends JpaRepository<CygPnlSettlementDaily, Long> {

}
