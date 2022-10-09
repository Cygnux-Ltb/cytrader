package io.cygnux.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author yellow013
 * <p>
 * Bar
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_bar")
@Entity
public final class BarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private long uid;

    @Column(name = "instrument_code")
    private String instrumentCode;

    @Column(name = "trading_day")
    private int tradingDay;

    @Column(name = "time_point")
    private int timePoint;

    @Column(name = "open")
    private double open;

    @Column(name = "high")
    private double high;

    @Column(name = "low")
    private double low;

    @Column(name = "close", nullable = false)
    private double close;

    @Column(name = "volume")
    private double volume;

    @Column(name = "turnover")
    private long turnover;

}
