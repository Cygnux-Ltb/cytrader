package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.ColumnDefinition;
import io.cygnus.repository.constant.CommonQueryColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

// StrategyParam

/**
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_strategy_param")
@Entity(name = "cyg_strategy_param")
public final class CygStrategyParam {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonQueryColumn.STRATEGY_ID)
    private int strategyId;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_type")
    private String paramType;

    @Column(name = "param_value")
    private String paramValue;

}
