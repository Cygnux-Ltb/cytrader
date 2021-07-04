package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygInfoEntity;

/**
 * @author yellow013
 * <p>
 * CygInfoDao
 */

@Repository
public interface CygInfoDao extends JpaRepository<CygInfoEntity, Long> {


}
