package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 盈亏
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class PnlDTO {

    /**
     * 策略ID
     */
    private int strategyId;

    /**
     * 交易标的代码 [*]
     */
    private String instrumentCode;

    /**
     * 交易日 [*]
     */
    private int tradingDay;

    /**
     * 平均多头价格
     */
    private double avgBuyPrice;

    /**
     * 平均空头价格
     */
    private double avgSellPrice;

    /**
     * 多头数量
     */
    private int buyQuantity;

    /**
     * 空头数量
     */
    private int sellQuantity;

    /**
     * 今多头数量
     */
    private int todayLong;

    /**
     * 今空头数量
     */
    private int todayShort;

    /**
     * 昨多头数量
     */
    private int yesterdayLong;

    /**
     * 昨空头数量
     */
    private int yesterdayShort;

    /**
     * 净头寸
     */
    private int netPosition;

    /**
     * 聚合交易手续费
     */
    private double aggregatedFee;

    /**
     * 成交额
     */
    private int turnover;

    /**
     * 认证状态
     */
    private int approved;

}
