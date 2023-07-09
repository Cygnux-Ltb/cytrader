package io.cygnuxltb.console.persistence.entity;

import io.cygnuxltb.console.persistence.CommonColumn;
import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易标的结算表
 * InstrumentSettlement Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cy_instrument_settlement")
public final class InstrumentSettlementEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode
     */
    @Column(name = CommonColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay [*]
     */
    @Column(name = CommonColumn.TRADING_DAY)
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
