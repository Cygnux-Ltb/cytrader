package io.cygnux.console.entity;

import io.cygnux.console.dao.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Account database entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_account")
public final class AccountEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonQueryColumn.ACCOUNT_ID)
    private int accountId;

    @Column(name = CommonQueryColumn.SUB_ACCOUNT_ID)
    private int subAccountId;

    @Column(name = CommonQueryColumn.BROKER)
    private String broker;

    @Column(name = CommonQueryColumn.INVESTOR_ID)
    private String investorId;

    @Column(name = "adaptor_type")
    private String adaptorType;

}

