package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * InstrumentSettlement Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_instrument_settlement")
public final class InstrumentSettlementEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode
     */
    @Column(name = CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay [*]
     */
    @Column(name = CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * lastPrice
     */
    @Column(name = "last_price")
    private double lastPrice;

    /**
     * openPrice
     */
    @Column(name = "open_price")
    private double openPrice;

    /**
     * settlementPrice
     */
    @Column(name = "settlement_price")
    private double settlementPrice;

}
