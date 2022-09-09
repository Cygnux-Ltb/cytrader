package io.cygnux.repository.entity;

import io.cygnux.repository.constant.RdbColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.repository.constant.RdbColumn.*;

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
    @Column(name = UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = ACCOUNT_ID, nullable = false)
    private int accountId;

    @Column(name = SUB_ACCOUNT_ID, nullable = false)
    private int subAccountId;

    @Column(name = BROKER_ID, nullable = false)
    private String brokerId;

    @Column(name = INVESTOR_ID)
    private String investorId;

    @Column(name = "adaptor_type", nullable = false)
    private String adaptorType;

}
