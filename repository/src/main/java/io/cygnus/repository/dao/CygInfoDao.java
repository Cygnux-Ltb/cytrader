package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygInfoEntity;

/**
 * 
 * CygInfo DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface CygInfoDao extends JpaRepository<CygInfoEntity, Integer> {

}
