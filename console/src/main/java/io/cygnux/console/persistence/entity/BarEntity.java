package io.cygnux.console.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author yellow013
 * <p>
 * Bar of 1 minute
 */
@Data
@Entity
@Table(name = "cyg_bar")
public final class BarEntity {

    @Id
    @Column(name ="uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name ="instrument_code")
    private String instrumentCode;

    @Column(name ="trading_day")
    private int tradingDay;

    @Column(name ="actual_date")
    private int actualDate;

    @Column(name ="time_point")
    private int timePoint;

    @Column(name ="open")
    private double open;

    @Column(name ="high")
    private double high;

    @Column(name ="low")
    private double low;

    @Column(name ="close")
    private double close;

    @Column(name ="volume")
    private double volume;

    @Column(name ="turnover")
    private long turnover;

}
