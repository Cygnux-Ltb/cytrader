package io.cygnus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.cygnus.persistence.entity.CygInstrumentSettlement;

public interface CygInstrumentSettlementRepository extends JpaRepository<CygInstrumentSettlement, Long> {

}
