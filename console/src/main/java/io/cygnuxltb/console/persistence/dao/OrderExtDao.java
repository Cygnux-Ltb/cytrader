package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.OrderExtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderExtDao extends JpaRepository<OrderExtEntity, Long> {

    
}
