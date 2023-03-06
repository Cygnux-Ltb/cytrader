package io.cygnux.console.persistence.dao;

import io.cygnux.console.persistence.entity.OrderExtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderExtDao extends JpaRepository<OrderExtEntity, Long> {

    
}
