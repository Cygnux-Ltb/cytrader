package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * K线表
 * Bar Entity of 1 minute
 * <p>
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class BarDTO {

    private long uid;

    private String instrumentCode;

    private int tradingDay;

    private int actualDate;

    private int timePoint;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    private long turnover;

}
