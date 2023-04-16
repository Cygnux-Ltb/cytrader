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
 * 交易账户表
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cy_indicator")
public final class IndicatorEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonColumn.ACCOUNT_ID)
    private int accountId;

    @Column(name = CommonColumn.SUB_ACCOUNT_ID)
    private int subAccountId;

    @Column(name = CommonColumn.BROKER_ID)
    private String brokerId;

    @Column(name = CommonColumn.INVESTOR_ID)
    private String investorId;

    @Column(name = "adaptor_type")
    private String adaptorType;

}

