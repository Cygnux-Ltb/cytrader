package io.cygnus.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.persistence.entity.base.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygPnlDaily
 *
 */
@Entity
@Table(name = "cyg_pnl_daily")
@Getter
@Setter
@Accessors(chain = true)
public final class CygPnlDaily {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "strategy_id")
	private Integer strategyId;
	public static final String COLUMN_StrategyID = "strategy_id";

	// InstrumentID varchar 31
	@Column(name = "instrument_code")
	private String instrumentCode;
	public static final String COLUMN_InstrumentCode = "instrument_code";

	// TradingDay date
	@Column(name = "trading_day", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	// EventType char
	@Column(name = "event_type")
	private String eventType;
	public static final String COLUMN_EventType = "event_type";

	// EventID varchar 21
	@Column(name = "event_id")
	private String eventId;
	public static final String COLUMN_NAME_EventID = "event_id";

	// AvgBuyPrice double 19_4
	@Column(name = "avg_buy_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double avgBuyPrice;
	public static final String COLUMN_AvgBuyPrice = "avg_buy_price";

	// AvgSellPrice double 19_4
	@Column(name = "avg_sell_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double avgSellPrice;
	public static final String COLUMN_AvgSellPrice = "avg_sell_price";

	// BuyQuantity int
	@Column(name = "buy_quantity")
	private Integer buyQuantity;
	public static final String COLUMN_BuyQuantity = "buy_quantity";

	// SellQuantity int
	@Column(name = "sell_quantity")
	private Integer sellQuantity;
	public static final String COLUMN_SellQuantity = "sell_quantity";

	// TodayLong int
	@Column(name = "today_long")
	private Integer todayLong;
	public static final String COLUMN_TodayLong = "today_long";

	// YesterdayLong int
	@Column(name = "yesterday_long")
	private Integer yesterdayLong;
	public static final String COLUMN_YesterdayLong = "yesterday_long";

	// TodayShort int
	@Column(name = "today_short")
	private Integer todayShort;
	public static final String COLUMN_TodayShort = "today_short";

	// YesterdayShort int
	@Column(name = "yesterday_short")
	private Integer yesterdayShort;
	public static final String COLUMN_YesterdayShort = "yesterday_short";

	// NetPosition int
	@Column(name = "net_position")
	private Integer netPosition;
	public static final String COLUMN_NetPosition = "net_position";

	// AggregatedFee double 19_4
	@Column(name = "aggregated_fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double aggregatedFee;
	public static final String COLUMN_AggregatedFee = "aggregated_fee";

	// Approved char
	@Column(name = "approved")
	private char approved;
	public static final String COLUMN_Approved = "approved";

	// Turnover int
	@Column(name = "turnover")
	private Integer turnover;
	public static final String COLUMN_Turnover = "turnover";

}
