package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 1分钟K线
 * <p>
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class BarH1DTO {

    /**
     * 交易标的代码 [*]
     */
    private String instrumentCode;

    /**
     * 交易日 [*]
     */
    private int tradingDay;

    /**
     * 实际日期 [*]
     */
    private int actualDate;

    /**
     * 时间点 [*]
     */
    private int timePoint;

    /**
     * 开盘价 [*]
     */
    private double open;

    /**
     * 最高价 [*]
     */
    private double high;

    /**
     * 最低价 [*]
     */
    private double low;

    /**
     * 收盘价 [*]
     */
    private double close;

    /**
     * 成交量 [*]
     */
    private double volume;

    /**
     * 成交额 [*]
     */
    private double turnover;

}
