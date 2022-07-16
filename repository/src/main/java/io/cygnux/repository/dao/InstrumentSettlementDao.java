package io.cygnux.repository.dao;

import io.cygnux.repository.entities.TInstrumentSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * InstrumentSettlement DAO
 *
 * @author yellow013
 */
@Repository
public interface InstrumentSettlementDao extends JpaRepository<TInstrumentSettlement, Long> {

    List<TInstrumentSettlement> queryByInstrumentCodeAndTradingDay(
            @Nullable String instrumentCode, int tradingDay);

}
