package io.cygnux.repository.entity;

import io.cygnux.repository.constant.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.repository.constant.RdbColumn.INSTRUMENT_CODE;
import static io.cygnux.repository.constant.RdbColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.RdbColumn.TRADING_DAY;
import static io.cygnux.repository.constant.RdbColumn.UID;

/**
 * PnlDaily Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_pnl")
@Entity
public final class PnlEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategyId
     */
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @Column(name = TRADING_DAY)
    private int tradingDay;

    /**
     * avgBuyPrice
     */
    @Column(name = "avg_buy_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double avgBuyPrice;

    /**
     * avgSellPrice
     */
    @Column(name = "avg_sell_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double avgSellPrice;

    /**
     * buyQuantity
     */
    @Column(name = "buy_quantity")
    private int buyQuantity;

    /**
     * sellQuantity
     */
    @Column(name = "sell_quantity")
    private int sellQuantity;

    /**
     * todayLong
     */
    @Column(name = "today_long")
    private int todayLong;

    /**
     * todayShort
     */
    @Column(name = "today_short")
    private int todayShort;

    /**
     * yesterdayLong
     */
    @Column(name = "yesterday_long")
    private int yesterdayLong;

    /**
     * yesterdayShort
     */
    @Column(name = "yesterday_short")
    private int yesterdayShort;

    /**
     * netPosition
     */
    @Column(name = "net_position")
    private int netPosition;

    /**
     * aggregatedFee
     */
    @Column(name = "aggregated_fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double aggregatedFee;

    /**
     * approved
     */
    @Column(name = "approved")
    private int approved;

    /**
     * turnover
     */
    @Column(name = "turnover")
    private int turnover;

}
