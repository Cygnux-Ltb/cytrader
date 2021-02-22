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

// StrategyInstrumentPNLDaily
@Entity
@Table(name = "StrategyInstrumentPNLDaily")
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyInstrumentPNLDaily {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static final String COLUMN_NAME_StrategyID = "StrategyID";

	// InstrumentID varchar 31
	@Column(name = "InstrumentID")
	private String instrumentId;
	public static final String COLUMN_NAME_InstrumentID = "InstrumentID";

	// TradingDay date
	@Column(name = "TradingDay", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	// EventType char
	@Column(name = "EventType")
	private String eventType;
	public static final String COLUMN_NAME_EventType = "EventType";

	// EventID varchar 21
	@Column(name = "EventID")
	private String eventId;
	public static final String COLUMN_NAME_EventID = "EventID";

	// AvgBuyPrice double 19_4
	@Column(name = "AvgBuyPrice", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double avgBuyPrice;
	public static final String COLUMN_NAME_AvgBuyPrice = "AvgBuyPrice";

	// AvgSellPrice double 19_4
	@Column(name = "AvgSellPrice", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double avgSellPrice;
	public static final String COLUMN_NAME_AvgSellPrice = "AvgSellPrice";

	// BuyQuantity int
	@Column(name = "BuyQuantity")
	private Integer buyQuantity;
	public static final String COLUMN_NAME_BuyQuantity = "BuyQuantity";

	// SellQuantity int
	@Column(name = "SellQuantity")
	private Integer sellQuantity;
	public static final String COLUMN_NAME_SellQuantity = "SellQuantity";

	// TodayLong int
	@Column(name = "TodayLong")
	private Integer todayLong;
	public static final String COLUMN_NAME_TodayLong = "TodayLong";

	// YesterdayLong int
	@Column(name = "YesterdayLong")
	private Integer yesterdayLong;
	public static final String COLUMN_NAME_YesterdayLong = "YesterdayLong";

	// TodayShort int
	@Column(name = "TodayShort")
	private Integer todayShort;
	public static final String COLUMN_NAME_TodayShort = "TodayShort";

	// YesterdayShort int
	@Column(name = "YesterdayShort")
	private Integer yesterdayShort;
	public static final String COLUMN_NAME_YesterdayShort = "YesterdayShort";

	// NetPosition int
	@Column(name = "NetPosition")
	private Integer netPosition;
	public static final String COLUMN_NAME_NetPosition = "NetPosition";

	// AggregatedFee double 19_4
	@Column(name = "AggregatedFee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double aggregatedFee;
	public static final String COLUMN_NAME_AggregatedFee = "AggregatedFee";

	// Approved char
	@Column(name = "Approved")
	private char approved;
	public static final String COLUMN_NAME_Approved = "Approved";

	// Turnover int
	@Column(name = "Turnover")
	private Integer turnover;
	public static final String COLUMN_NAME_Turnover = "Turnover";

}
