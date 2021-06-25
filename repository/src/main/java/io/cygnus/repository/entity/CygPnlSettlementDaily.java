package io.cygnus.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.ColumnDefinition;
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
public final class CygPnlSettlementDaily {

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

	// Position int
	@Column(name = "position")
	private Integer position;
	public static final String COLUMN_Position = "position";

	// PNLGross double 19_4
	@Column(name = "pnl_total", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double pnlTotal;
	public static final String COLUMN_PnlTotal = "pnl_total";

	// PNLNet double 19_4
	@Column(name = "pnl_net", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double pnlNet;
	public static final String COLUMN_PnlNet = "pnl_net";

	// TranscationCost double 19_4
	@Column(name = "transcation_cost", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double transcationCost;
	public static final String COLUMN_TranscationCost = "transcation_cost";

	// Exposure double 19_4
	@Column(name = "exposure", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double exposure;
	public static final String COLUMN_Exposure = "exposure";

	// Approved char
	@Column(name = "approved")
	private char approved;
	public static final String COLUMN_Approved = "approved";

}
