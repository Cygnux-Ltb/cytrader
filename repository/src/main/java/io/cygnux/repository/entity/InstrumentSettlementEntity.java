package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * InstrumentSettlement Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_instrument_settlement")
public final class InstrumentSettlementEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    /**
     * instrumentCode
     */
    @TableField(CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * tradingDay [*]
     */
    @TableField(CommonQueryColumn.TRADING_DAY)
    private int tradingDay;

    /**
     * lastPrice
     */
    @TableField("last_price")
    private double lastPrice;

    /**
     * openPrice
     */
    @TableField("open_price")
    private double openPrice;

    /**
     * settlementPrice
     */
    @TableField("settlement_price")
    private double settlementPrice;

}
