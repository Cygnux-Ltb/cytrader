package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.constant.ColumnDefinition;
import io.cygnux.console.persistence.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * PnlSettlement Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_pnl_settlement")
public final class PnlSettlementEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategyId
     */
    @Column(name = CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode
     */
    @Column(name =CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @Column(name =CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * position
     */
    @Column(name ="position")
    private int position;

    /**
     * pnlTotal
     */
    @Column(name ="pnl_total")
    private double pnlTotal;

    /**
     * pnlNet
     */
    @Column(name ="pnl_net")
    private double pnlNet;

    /**
     * tradeCost
     */
    @Column(name ="trade_cost")
    private double tradeCost;

    /**
     * exposure
     */
    @Column(name ="exposure")
    private double exposure;

    /**
     * approved
     */
    @Column(name ="approved")
    private int approved;

}
