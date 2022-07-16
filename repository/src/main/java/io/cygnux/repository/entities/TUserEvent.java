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
@Entity(name = TBar.ENTITY_NAME)
public class TUserEvent {

    @Id
    @Column(name = "uid", nullable = false)
    private long uid;

    public final static String ENTITY_NAME = "TUserEvent";

}
