package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.CommonColumn;
import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonColumn.STRATEGY_ID)
    private int strategyId;

    @Column(name = CommonColumn.STRATEGY_NAME)
    private String strategyName;

    @Column(name ="strategy_owner")
    private String strategyOwner;

    @Column(name ="strategy_info")
    private String strategyInfo;

}
