package io.cygnux.repository.entities;

import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "it_param")
@Entity(name = ItParam.EntityName)
public final class ItParam {

    public final static String EntityName = "ItParam";

    @Id
    @Column(name = CommonColumn.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonColumn.STRATEGY_ID)
    private int strategyId;

    @Column(name = CommonColumn.STRATEGY_NAME)
    private String strategyName;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_type")
    private String paramType;

    @Column(name = "param_value")
    private String paramValue;

}
