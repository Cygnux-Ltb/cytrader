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

// TradeableInstrument
@Entity
@Table(name = "TradeableInstrument")
@Getter
@Setter
@Accessors(chain = true)
public final class  TradeableInstrument  {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// Symbol varchar 10
	@Column(name = "Symbol")
	private String symbol;
	public static final String COLUMN_NAME_Symbol = "Symbol";

	// InsrumentID varchar 31
	@Column(name = "InstrumentID")
	private String instrumentId;
	public static final String COLUMN_NAME_InstrumentID = "InstrumentID";

	// TradingDay date
	@Column(name = "TradingDay", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	// TradeableType char
	@Column(name = "TradeableType")
	private String tradeableType;
	public static final String COLUMN_NAME_TradeableType = "TradeableType";

}
