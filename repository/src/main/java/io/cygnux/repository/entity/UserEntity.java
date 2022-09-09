package io.cygnux.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.repository.constant.RdbColumn.UID;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_bar")
@Entity
public class UserEntity {

    @Id
    @Column(name = UID, nullable = false)
    private long uid;

    private long userId;

    private String username;

    private String password;

    private String email;

    private String phone;

    private int subAccountId;

}
