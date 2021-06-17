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

// InstrumentSettlementPrice
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "InstrumentSettlementPrice")
@Getter
@Setter
@Accessors(chain = true)
public final class InstrumentSettlementPrice {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// TradingDay date
	@Column(name = "trading_day", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	// InstrumentID varchar 31
	@Column(name = "instrument_id")
	private String instrumentId;
	public static final String COLUMN_NAME_InstrumentID = "InstrumentID";

	// SettlementPrice double 19_4
	@Column(name = "settlement_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double settlementPrice;
	public static final String COLUMN_NAME_SettlementPrice = "SettlementPrice";

}
