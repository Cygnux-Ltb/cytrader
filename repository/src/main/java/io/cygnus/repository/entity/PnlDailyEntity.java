package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.ColumnDefinition;
import io.cygnus.repository.constant.CommonColumn;
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
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_pnl_daily")
@Entity(name = "cyg_pnl_daily")
public final class PnlDailyEntity {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	/**
	 * strategyId
	 */
	@Column(name = CommonColumn.STRATEGY_ID)
	private int strategyId;

	/**
	 * instrumentCode
	 */
	@Column(name = CommonColumn.INSTRUMENT_CODE)
	private String instrumentCode;

	/**
	 * tradingDay
	 */
	@Column(name = CommonColumn.TRADING_DAY)
	private int tradingDay;

	/**
	 * avgBuyPrice
	 */
	@Column(name = "avg_buy_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double avgBuyPrice;

	/**
	 * avgSellPrice
	 */
	@Column(name = "avg_sell_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double avgSellPrice;

	/**
	 * buyQuantity
	 */
	@Column(name = "buy_quantity")
	private int buyQuantity;

	/**
	 * sellQuantity
	 */
	@Column(name = "sell_quantity")
	private int sellQuantity;

	/**
	 * todayLong
	 */
	@Column(name = "today_long")
	private int todayLong;

	/**
	 * todayShort
	 */
	@Column(name = "today_short")
	private int todayShort;

	/**
	 * yesterdayLong
	 */
	@Column(name = "yesterday_long")
	private int yesterdayLong;

	/**
	 * yesterdayShort
	 */
	@Column(name = "yesterday_short")
	private int yesterdayShort;

	/**
	 * netPosition
	 */
	@Column(name = "net_position")
	private int netPosition;

	/**
	 * aggregatedFee
	 */
	@Column(name = "aggregated_fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double aggregatedFee;

	/**
	 * approved
	 */
	@Column(name = "approved")
	private int approved;

	/**
	 * turnover
	 */
	@Column(name = "turnover")
	private int turnover;

}
