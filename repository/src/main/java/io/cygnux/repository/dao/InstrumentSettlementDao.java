package io.cygnux.repository.dao;

import io.cygnux.repository.entities.StInstrumentSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * InstrumentSettlement DAO
 *
 * @author yellow013
 */
@Repository
public interface InstrumentSettlementDao extends JpaRepository<StInstrumentSettlement, Long> {

    @Query("SELECT '*' FROM #{#entityName} e "
            + " WHERE "
            + " e.instrumentCode LIKE :instrumentCode% "
            + " AND "
            + " e.tradingDay = :tradingDay ")
    List<StInstrumentSettlement> query(@Nullable String instrumentCode,
                                       int tradingDay);

}
