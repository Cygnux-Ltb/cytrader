package io.cygnux.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.repository.constant.RdbColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.RdbColumn.STRATEGY_NAME;
import static io.cygnux.repository.constant.RdbColumn.UID;

/**
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_param")
@Entity
public final class ParamEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = STRATEGY_ID)
    private int strategyId;

    @Column(name = STRATEGY_NAME)
    private String strategyName;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_type")
    private String paramType;

    @Column(name = "param_value")
    private String paramValue;

}
