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

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cy_portfolio")
public class PortfolioEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * userId [*]
     */
    @Column(name = CommonColumn.USER_ID)
    private int userId;

    /**
     * instrumentCode [*]
     */
    @Column(name = CommonColumn.INSTRUMENT_CODE)
    private String instrumentCode;

}
