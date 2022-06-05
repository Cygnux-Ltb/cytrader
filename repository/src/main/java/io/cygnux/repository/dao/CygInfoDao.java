package io.cygnux.repository.dao;

import io.cygnux.repository.entity.CygInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
