package io.cygnux.repository.entities;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * InstrumentSettlement Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "st_instrument_static")
@Entity(name = StInstrumentStatic.EntityName)
public final class StInstrumentStatic {

    public final static String EntityName = "StInstrumentStatic";

    @Id
    @Column(name = CommonColumn.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode
     */
    @Column(name = CommonColumn.INSTRUMENT_CODE, nullable = false)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @Column(name = CommonColumn.TRADING_DAY, nullable = false)
    private int tradingDay;

    /**
     * lastPrice
     */
    @Column(name = "last_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double lastPrice;

    /**
     * openPrice
     */
    @Column(name = "open_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double openPrice;

    /**
     * settlementPrice
     */
    @Column(name = "settlement_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double settlementPrice;

}
