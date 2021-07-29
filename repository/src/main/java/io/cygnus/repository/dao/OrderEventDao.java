package io.cygnus.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.OrderEventEntity;

/**
 * OrderEvent DAO
 * 
 * @author yellow013
 * 
 */
@Repository
public interface OrderEventDao extends JpaRepository<OrderEventEntity, Long> {

	List<OrderEventEntity> queryByTradingDay(int tradingDay);

	List<OrderEventEntity> queryByOrdSysId(long ordSysId);

}
