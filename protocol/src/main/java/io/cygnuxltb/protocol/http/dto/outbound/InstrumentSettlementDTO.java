package io.cygnuxltb.protocol.http.dto.outbound;

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
public final class InstrumentSettlementDTO {

    private long uid;

    /**
     * instrumentCode
     */
    private String instrumentCode;

    /**
     * tradingDay [*]
     */
    private int tradingDay;

    /**
     * lastPrice
     */
    private double lastPrice;

    /**
     * openPrice
     */
    private double openPrice;

    /**
     * settlementPrice
     */
    private double settlementPrice;

}
