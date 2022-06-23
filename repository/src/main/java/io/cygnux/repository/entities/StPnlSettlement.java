package io.cygnux.repository.entities;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * PnlSettlementDaily Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "st_pnl_settlement")
@Entity(name = StPnlSettlement.EntityName)
public final class StPnlSettlement {

    public final static String EntityName = "StPnlSettlement";

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
     * position
     */
    @Column(name = "position")
    private int position;

    /**
     * pnlTotal
     */
    @Column(name = "pnl_total", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double pnlTotal;

    /**
     * pnlNet
     */
    @Column(name = "pnl_net", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double pnlNet;

    /**
     * tradeCost
     */
    @Column(name = "trade_cost", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double tradeCost;

    /**
     * exposure
     */
    @Column(name = "exposure", columnDefinition = ColumnDefinition.DECIMAL_19_4)
    private double exposure;

    /**
     * approved
     */
    @Column(name = "approved")
    private int approved;

}
