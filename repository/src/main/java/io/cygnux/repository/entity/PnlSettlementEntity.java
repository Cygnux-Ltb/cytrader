package io.cygnux.repository.entity;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.RdbColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.repository.constant.RdbColumn.*;

/**
 * PnlSettlementDaily Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_pnl_settlement")
@Entity
public final class PnlSettlementEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * strategyId
     */
    @Column(name = STRATEGY_ID)
    private int strategyId;

    /**
     * instrumentCode
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay
     */
    @Column(name = RdbColumn.TRADING_DAY)
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
