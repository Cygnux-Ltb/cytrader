package io.cygnus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.cygnus.persistence.entity.CygPnlDaily;

public interface CygPnlDailyRepository extends JpaRepository<CygPnlDaily, Long> {

}
