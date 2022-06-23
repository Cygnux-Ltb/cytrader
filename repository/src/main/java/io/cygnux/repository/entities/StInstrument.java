package io.cygnux.repository.entities;

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
@Table(name = "st_instrument")
@Entity(name = StInstrument.EntityName)
public final class StInstrument {

    public final static String EntityName = "StInstrument";

    @Id
    @Column(name = CommonColumn.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode
     */
    @Column(name = CommonColumn.INSTRUMENT_CODE, nullable = false)
    private String instrumentCode;

    /**
     * exchangeCode
     */
    @Column(name = "exchangeCode", nullable = false)
    private String exchangeCode;

    /**
     * fee
     */
    @Column(name = "fee", nullable = false, columnDefinition = ColumnDefinition.DECIMAL_19_8)
    private double fee;

    /**
     * tradeable
     */
    @Column(name = "tradeable")
    private boolean tradeable;

}
