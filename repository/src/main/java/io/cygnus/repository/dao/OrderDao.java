package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.OrderEntity;

import java.util.List;

/**
 * @author yellow013
 * <p>
 * OrderDao
 */
@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

    @Query("")
    List<OrderEntity> query();


}
