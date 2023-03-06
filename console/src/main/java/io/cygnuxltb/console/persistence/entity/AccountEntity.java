package io.cygnuxltb.console.persistence.entity;

import io.cygnuxltb.console.persistence.CommonColumn;
import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Account Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_account")
public final class AccountEntity {

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

