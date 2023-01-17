package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cyg_user")
public class UserEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = CommonQueryColumn.USER_ID)
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = CommonQueryColumn.SUB_ACCOUNT_ID)
    private int subAccountId;

}
