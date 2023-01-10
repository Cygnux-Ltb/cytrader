package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_param")
public final class ParamEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    @TableField(CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

    @TableField(CommonQueryColumn.STRATEGY_NAME)
    private String strategyName;

    @TableField("owner_type")
    private String ownerType;

    @TableField("owner")
    private String owner;

    @TableField("param_name")
    private String paramName;

    @TableField("param_type")
    private String paramType;

    @TableField("param_value")
    private String paramValue;

}
