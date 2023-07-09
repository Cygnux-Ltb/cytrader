package io.cygnuxltb.console.persistence.entity;

import io.cygnuxltb.console.persistence.CommonColumn;
import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 配置信息表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cy_param")
public final class ParamEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonColumn.STRATEGY_ID)
    private int strategyId;

    @Column(name = CommonColumn.STRATEGY_NAME)
    private String strategyName;

    @Column(name = "owner_type")
    private String ownerType;

    @Column(name = "owner")
    private String owner;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_type")
    private String paramType;

    @Column(name = "param_value")
    private String paramValue;

}
