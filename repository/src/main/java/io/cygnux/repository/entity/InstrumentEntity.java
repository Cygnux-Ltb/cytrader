package io.cygnux.repository.entity;

import io.cygnux.repository.constant.ColumnDefinition;
import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Instrument Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_instrument")
@Entity
public final class InstrumentEntity {

    @Id
    @Column(name = CommonColumn.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode
     */
    @Column(name = CommonColumn.INSTRUMENT_CODE, nullable = false)
    private String instrumentCode;

    @Column(name = "instrument_type", nullable = false)
    private String instrumentType;

    /**
     * exchangeCode
     */
    @Column(name = "exchange_code", nullable = false)
    private String exchangeCode;

    /**
     * fee
     */
    @Column(name = "fee", nullable = false, columnDefinition = ColumnDefinition.DECIMAL_19_8)
    private double fee;

    /**
     * tradeable
     */
    @Column(name = "tradable")
    private boolean tradable;

}
