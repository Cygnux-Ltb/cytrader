package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.StrategyEntity;

/**
 * Strategy DAO
 * 
 * @author yellow013
 * 
 */

@Repository
public interface StrategyDao extends JpaRepository<StrategyEntity, Integer> {

}
