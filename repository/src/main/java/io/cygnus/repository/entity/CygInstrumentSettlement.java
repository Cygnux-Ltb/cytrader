package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygInstrumentSettlement
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_instrument_settlement")
@Entity(name = "cyg_instrument_settlement")
public final class CygInstrumentSettlement {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	// TradingDay date
	@Column(name = "trading_day")
	private int tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	// InstrumentID varchar 31
	@Column(name = "instrument_code")
	private String instrumentCode;
	public static final String COLUMN_InstrumentCode = "instrument_code";

	// SettlementPrice double 19_4
	@Column(name = "last_price")
	private long lastPrice;
	public static final String COLUMN_LastPrice = "last_price";

	// SettlementPrice double 19_4
	@Column(name = "settlement_price")
	private long settlementPrice;
	public static final String COLUMN_SettlementPrice = "settlement_price";

}
