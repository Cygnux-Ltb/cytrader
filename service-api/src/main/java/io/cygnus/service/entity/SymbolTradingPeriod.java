package io.cygnus.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

// InstrumentTradingPeriod
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "SymbolTradingPeriod")
@Getter
@Setter
@Accessors(chain = true)
public final class SymbolTradingPeriod {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// Symbol varchar 10
	@Column(name = "Symbol")
	private String symbol;
	public static final String COLUMN_NAME_Symbol = "Symbol";

	// TradingPeriodType char
	@Column(name = "TradingPeriodType")
	private Integer tradingPeriodType;
	public static final String COLUMN_NAME_TradingPeriodType = "TradingPeriodType";

	// StartTime int
	@Column(name = "StartTime")
	private Integer startTime;
	public static final String COLUMN_NAME_StartTime = "StartTime";

	// EndTime int
	@Column(name = "EndTime")
	private Integer endTime;
	public static final String COLUMN_NAME_EndTime = "EndTime";

}
