package io.cygnux.repository.entity;

import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_account")
@Entity
public final class AccountEntity {

    @Id
    @Column(name = CommonColumn.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonColumn.ACCOUNT_ID, nullable = false)
    private int accountId;

    @Column(name = CommonColumn.SUB_ACCOUNT_ID, nullable = false)
    private int subAccountId;

    @Column(name = CommonColumn.BROKER_ID, nullable = false)
    private String brokerId;

    @Column(name = CommonColumn.INVESTOR_ID)
    private String investorId;

    @Column(name = "adaptor_type", nullable = false)
    private String adaptorType;

}
