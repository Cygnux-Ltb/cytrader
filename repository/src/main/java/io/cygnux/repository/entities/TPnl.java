package io.cygnux.repository.entities;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * PnlDaily Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "shr_pnl")
@Entity(name = Pnl.EntityName)
public final class Pnl {

    public final static String EntityName = "StPnl";

    @Id
    @Column(name = CommonColumn.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategyId
     */
    @Column(name = CommonColumn.STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode
     */
    @Column(name = CommonColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @Column(name = CommonColumn.TRADING_DAY)
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
