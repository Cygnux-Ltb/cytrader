package io.cygnux.repository.dao;

import io.cygnux.repository.entity.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarDao extends JpaRepository<BarEntity, Long> {

    List<BarEntity> queryByInstrumentCodeAndTradingDay(
            String instrumentCode, long tradingDay);

}
