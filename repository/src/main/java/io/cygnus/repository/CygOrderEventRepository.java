package io.cygnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygOrderEvent;

/**
 * 
 * @author yellow013
 * 
 *         CygOrderEventRepository
 *
 */
@Repository
public interface CygOrderEventRepository extends JpaRepository<CygOrderEvent, Long> {

}
