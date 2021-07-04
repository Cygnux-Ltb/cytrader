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
 *         CygPnlSettlementDaily
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_pnl_settlement_daily")
@Entity(name = "cyg_pnl_settlement_daily")
public final class PnlSettlementDailyEntity {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

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
	 * position
	 */
	@Column(name = "position")
	private int position;

	/**
	 * pnlTotal
	 */
	@Column(name = "pnl_total", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double pnlTotal;

	/**
	 * pnlNet
	 */
	@Column(name = "pnl_net", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double pnlNet;

	/**
	 * tradeCost
	 */
	@Column(name = "trade_cost", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double tradeCost;

	/**
	 * exposure
	 */
	@Column(name = "exposure", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double exposure;

	/**
	 * approved
	 */
	@Column(name = "approved")
	private int approved;

}
