package io.cygnux.repository.dao;

import io.cygnux.repository.entities.TBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarDao extends JpaRepository<TBar, Long> {

    List<TBar> queryByInstrumentCodeAndTradingDay(
            @Param("instrumentCode") String instrumentCode,
            @Param("tradingDay") int tradingDay);

}
