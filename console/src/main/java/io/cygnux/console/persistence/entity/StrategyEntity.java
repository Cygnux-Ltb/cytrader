package io.cygnux.console.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static io.cygnux.console.dao.constant.CommonQueryColumn.STRATEGY_ID;
import static io.cygnux.console.dao.constant.CommonQueryColumn.STRATEGY_NAME;

/**
 * Strategy Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_strategy")
public final class StrategyEntity {

    @Id
    @Column(name ="uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name =STRATEGY_ID)
    private int strategyId;

    @Column(name =STRATEGY_NAME)
    private String strategyName;

    @Column(name ="strategy_owner")
    private String strategyOwner;

    @Column(name ="strategy_info")
    private String strategyInfo;

}
