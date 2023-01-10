package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import static io.cygnux.repository.constant.CommonQueryColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.CommonQueryColumn.STRATEGY_NAME;

/**
 * Strategy Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_strategy")
public final class StrategyEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    @TableField(STRATEGY_ID)
    private int strategyId;

    @TableField(STRATEGY_NAME)
    private String strategyName;

    @TableField("strategy_owner")
    private String strategyOwner;

    @TableField("strategy_info")
    private String strategyInfo;

}
