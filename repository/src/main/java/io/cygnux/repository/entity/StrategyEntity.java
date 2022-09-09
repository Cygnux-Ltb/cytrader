package io.cygnux.repository.entity;

import io.cygnux.repository.constant.RdbColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.repository.constant.RdbColumn.STRATEGY_ID;
import static io.cygnux.repository.constant.RdbColumn.STRATEGY_NAME;

/**
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_strategy")
@Entity
public final class StrategyEntity {

    @Id
    @Column(name = STRATEGY_ID)
    private int strategyId;

    @Column(name = STRATEGY_NAME)
    private String strategyName;

    @Column(name = "strategy_owner")
    private String strategyOwner;

    @Column(name = "strategy_info")
    private String strategyInfo;

}
