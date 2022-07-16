package io.cygnux.repository.entities;

import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_strategy")
@Entity(name = TStrategy.ENTITY_NAME)
public final class TStrategy {

    public final static String ENTITY_NAME = "TStrategy";

    @Id
    @Column(name = CommonColumn.STRATEGY_ID)
    private int strategyId;

    @Column(name = CommonColumn.STRATEGY_NAME)
    private String strategyName;

    @Column(name = "strategy_owner")
    private String strategyOwner;

    @Column(name = "strategy_info")
    private String strategyInfo;

}
