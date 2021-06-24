package io.cygnus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.persistence.entity.CygInstrument;

@Repository
public interface CygInstrumentRepository extends JpaRepository<CygInstrument, Long> {

}
