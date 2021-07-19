package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.BarEntity;

@Repository
public interface BarDao extends JpaRepository<BarEntity, Long> {

}
