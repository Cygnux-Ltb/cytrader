package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * Instrument Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_instrument")
public final class InstrumentEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    /**
     * instrumentCode [*]
     */
    @TableField(CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * instrumentType [*]
     */
    @TableField("instrument_type")
    private String instrumentType;

    /**
     * exchangeCode [*]
     */
    @TableField("exchange_code")
    private String exchangeCode;

    /**
     * fee
     */
    @TableField("fee")
    private double fee;

    /**
     * tradable
     */
    @TableField("tradable")
    private boolean tradable;

}
