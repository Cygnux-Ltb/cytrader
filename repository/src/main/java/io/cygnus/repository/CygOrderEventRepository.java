package io.cygnus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.persistence.entity.CygOrderEvent;

@Repository
public interface CygOrderEventRepository extends JpaRepository<CygOrderEvent, Long> {

}
