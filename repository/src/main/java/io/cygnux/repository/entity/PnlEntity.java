package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * PnlDaily Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_pnl")
public final class PnlEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    /**
     * strategyId
     */
    @TableField(CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode
     */
    @TableField(CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @TableField(CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * avgBuyPrice
     */
    @TableField("avg_buy_price")
    private double avgBuyPrice;

    /**
     * avgSellPrice
     */
    @TableField("avg_sell_price")
    private double avgSellPrice;

    /**
     * buyQuantity
     */
    @TableField("buy_quantity")
    private int buyQuantity;

    /**
     * sellQuantity
     */
    @TableField("sell_quantity")
    private int sellQuantity;

    /**
     * todayLong
     */
    @TableField("today_long")
    private int todayLong;

    /**
     * todayShort
     */
    @TableField("today_short")
    private int todayShort;

    /**
     * yesterdayLong
     */
    @TableField("yesterday_long")
    private int yesterdayLong;

    /**
     * yesterdayShort
     */
    @TableField("yesterday_short")
    private int yesterdayShort;

    /**
     * netPosition
     */
    @TableField("net_position")
    private int netPosition;

    /**
     * aggregatedFee
     */
    @TableField("aggregated_fee")
    private double aggregatedFee;

    /**
     * approved
     */
    @TableField("approved")
    private int approved;

    /**
     * turnover
     */
    @TableField("turnover")
    private int turnover;

}
