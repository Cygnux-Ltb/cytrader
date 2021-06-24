package io.cygnus.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.persistence.entity.CygOrder;

@Repository
public interface CygOrderRepository extends JpaRepository<CygOrder, Long> {

}
