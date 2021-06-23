package io.cygnus.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         Bar
 *
 */
@Data
@Entity
@Table(name = "Bar")
@Accessors(chain = true)
public final class Bar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long uid;

	// ThadID int
	@Column(name = "cyg_id")
	private Integer cygId;
	public static final String COLUMN_CygId = "cyg_id";

	// InstrumentID varchar 31
	@Column(name = "instrument_code")
	private String instrumentCode;
	public static final String COLUMN_InstrumentCode = "instrument_code";

	// TradingDay date
	@Column(name = "trading_day")
	private long tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	// Time datetime
	@Column(name = "time_point")
	private Integer timePoint;
	public static final String COLUMN_TimePoint = "time_point";

	// Open double 19_4
	@Column(name = "open")
	private long open;
	public static final String COLUMN_Open = "open";

	// High double 19_4
	@Column(name = "high")
	private long high;
	public static final String COLUMN_High = "high";

	// Low double 19_4
	@Column(name = "low")
	private long low;
	public static final String COLUMN_Low = "low";

	// Close double 19_4
	@Column(name = "close")
	private long close;
	public static final String COLUMN_Close = "close";

	// VWAP double 19_4
	@Column(name = "vwap")
	private long vwap;
	public static final String COLUMN_VWAP = "vwap";

	// VWAP double 19_4
	@Column(name = "last_volume")
	private long lastVolume;
	public static final String COLUMN_LastVolume = "last_volume";

}
