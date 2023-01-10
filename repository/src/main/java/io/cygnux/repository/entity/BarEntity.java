package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yellow013
 * <p>
 * Bar of 1 minute
 */
@Data
@TableName("cyg_bar")
public final class BarEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    @TableField("instrument_code")
    private String instrumentCode;

    @TableField("trading_day")
    private int tradingDay;

    @TableField("actual_date")
    private int actualDate;

    @TableField("time_point")
    private int timePoint;

    @TableField("open")
    private double open;

    @TableField("high")
    private double high;

    @TableField("low")
    private double low;

    @TableField("close")
    private double close;

    @TableField("volume")
    private double volume;

    @TableField("turnover")
    private long turnover;

}
