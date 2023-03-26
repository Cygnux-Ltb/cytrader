package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 盈亏表
 * Pnl Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class PnlDTO {

    private long uid;

    /**
     * strategyId
     */
    private int strategyId;

    /**
     * instrumentCode
     */
    private String instrumentCode;

    /**
     * tradingDay
     */
    private int tradingDay;

    /**
     * avgBuyPrice
     */
    private double avgBuyPrice;

    /**
     * avgSellPrice
     */
    private double avgSellPrice;

    /**
     * buyQuantity
     */
    private int buyQuantity;

    /**
     * sellQuantity
     */
    private int sellQuantity;

    /**
     * todayLong
     */
    private int todayLong;

    /**
     * todayShort
     */
    private int todayShort;

    /**
     * yesterdayLong
     */
    private int yesterdayLong;

    /**
     * yesterdayShort
     */
    private int yesterdayShort;

    /**
     * netPosition
     */
    private int netPosition;

    /**
     * aggregatedFee
     */
    private double aggregatedFee;

    /**
     * approved
     */
    private int approved;

    /**
     * turnover
     */
    private int turnover;

}
