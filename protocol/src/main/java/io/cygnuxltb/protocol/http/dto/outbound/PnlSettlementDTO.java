package io.cygnuxltb.protocol.http.dto.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 盈亏结算表
 * PnlSettlement Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class PnlSettlementDTO {

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
     * position
     */
    private int position;

    /**
     * pnlTotal
     */
    private double pnlTotal;

    /**
     * pnlNet
     */
    private double pnlNet;

    /**
     * tradeCost
     */
    private double tradeCost;

    /**
     * exposure
     */
    private double exposure;

    /**
     * approved
     */
    private int approved;

}
