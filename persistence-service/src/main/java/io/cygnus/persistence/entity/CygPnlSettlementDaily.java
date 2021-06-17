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

// StrategyInstrumentPNLSettlementDaily
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "StrategyInstrumentPNLSettlementDaily")
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyInstrumentPNLSettlementDaily  {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static String COLUMN_NAME_StrategyID = "StrategyID";

	// InstrumentID varchar 31
	@Column(name = "InstrumentID")
	private String instrumentId;
	public static String COLUMN_NAME_InstrumentID = "InstrumentID";

	// TradingDay date
	@Column(name = "TradingDay", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static String COLUMN_NAME_TradingDay = "TradingDay";

	// Position int
	@Column(name = "Position")
	private Integer position;
	public static String COLUMN_NAME_Position = "Position";

	// PNLGross double 19_4
	@Column(name = "PNLGross", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double pnlGross;
	public static String COLUMN_NAME_PNLGross = "PNLGross";

	// PNLNet double 19_4
	@Column(name = "PNLNet", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double pnlNet;
	public static String COLUMN_NAME_PNLNet = "PNLNet";

	// TranscationCost double 19_4
	@Column(name = "TranscationCost", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double transcationCost;
	public static String COLUMN_NAME_TranscationCost = "TranscationCost";

	// Exposure double 19_4
	@Column(name = "Exposure", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double exposure;
	public static String COLUMN_NAME_Exposure = "Exposure";

	// Approved char
	@Column(name = "Approved")
	private char approved;
	public static String COLUMN_NAME_Approved = "Approved";

}
