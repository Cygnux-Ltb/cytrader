package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * PnlSettlementDaily Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_pnl_settlement")
public final class PnlSettlementEntity {

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
     * position
     */
    @TableField("position")
    private int position;

    /**
     * pnlTotal
     */
    @TableField("pnl_total")
    private double pnlTotal;

    /**
     * pnlNet
     */
    @TableField("pnl_net")
    private double pnlNet;

    /**
     * tradeCost
     */
    @TableField("trade_cost")
    private double tradeCost;

    /**
     * exposure
     */
    @TableField("exposure")
    private double exposure;

    /**
     * approved
     */
    @TableField("approved")
    private int approved;

}
