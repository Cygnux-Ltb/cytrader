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
@Table(name = "t_instrument_settlement")
@Entity(name = TInstrumentSettlement.ENTITY_NAME)
public final class TInstrumentSettlement {

    public final static String ENTITY_NAME = "TInstrumentSettlement";

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
