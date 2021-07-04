package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.OrderEventEntity;

import java.util.List;

/**
 * @author yellow013
 * <p>
 * OrderEventDao
 */
@Repository
public interface OrderEventDao extends JpaRepository<OrderEventEntity, Long> {

    @Query("")
    List<OrderEventEntity> query();

}
