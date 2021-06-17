package io.cygnus.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.service.entity.base.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

// TimeBinner
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "TimeBinner")
@Getter
@Setter
@Accessors(chain = true)
public final class TimeBinner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	// ThadID int
	@Column(name = "CygId")
	private Integer cygId;
	public static final String COLUMN_NAME_CygId = "CygId";

	// InstrumentID varchar 31
	@Column(name = "InstrumentId")
	private String instrumentId;
	public static final String COLUMN_NAME_InstrumentId = "InstrumentId";

	// TradingDay date
	@Column(name = "TradingDay", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	// Time datetime
	@Column(name = "Time")
	private Integer time;
	public static final String COLUMN_NAME_Time = "Time";

	// Open double 19_4
	@Column(name = "Open", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double open;
	public static final String COLUMN_NAME_Open = "Open";

	// High double 19_4
	@Column(name = "High", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double high;
	public static final String COLUMN_NAME_High = "High";

	// Low double 19_4
	@Column(name = "Low", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double low;
	public static final String COLUMN_NAME_Low = "Low";

	// Close double 19_4
	@Column(name = "Close", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double close;
	public static final String COLUMN_NAME_Close = "Close";

	// VWAP double 19_4
	@Column(name = "VWAP", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double vwap;
	public static final String COLUMN_NAME_VWAP = "VWAP";

	// VWAP double 19_4
	@Column(name = "LastVolume")
	private Integer lastVolume;
	public static final String COLUMN_NAME_LastVolume = "LastVolume";

}
