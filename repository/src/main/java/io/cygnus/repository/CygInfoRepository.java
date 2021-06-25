package io.cygnus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygInfo;

/**
 * 
 * @author yellow013
 * 
 *         CygInfoRepository
 *
 */

@Repository
public interface CygInfoRepository extends JpaRepository<CygInfo, Long> {

}
