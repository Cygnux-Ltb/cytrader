package io.cygnux.console.entity;

import io.cygnux.console.dao.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Instrument Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_instrument")
public final class InstrumentEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * instrumentCode [*]
     */
    @Column(name = CommonQueryColumn.INSTRUMENT_CODE)
    private String instrumentCode;

    /**
     * instrumentType [*]
     */
    @Column(name = "instrument_type")
    private String instrumentType;

    /**
     * exchangeCode [*]
     */
    @Column(name = "exchange_code")
    private String exchangeCode;

    /**
     * fee
     */
    @Column(name = "fee")
    private double fee;

    /**
     * tradable
     */
    @Column(name = "tradable")
    private boolean tradable;

}
