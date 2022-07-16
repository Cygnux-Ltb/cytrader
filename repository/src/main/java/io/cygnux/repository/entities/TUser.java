package io.cygnux.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class EtUser {

    public final static String EntityName = "EtUser";

    @Id
    @Column()
    public long uid;

    long userId;

    int subAccountId;


}
