package io.cygnux.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_bar")
@Entity(name = TUser.ENTITY_NAME)
public class TUser {

    public final static String ENTITY_NAME = "TUser";

    @Id
    @Column(name = "uid", nullable = false)
    private long uid;

    private long userId;

    private String username;

    private String password;

    private String email;

    private String phone;




    private int subAccountId;

}
