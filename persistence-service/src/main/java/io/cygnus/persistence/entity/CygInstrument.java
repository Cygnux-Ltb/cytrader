package io.cygnus.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

// TradeableInstrument
/**
 * 
 * @author yellow013
 *
 */
@Data
@Entity
@Table(name = "cyg_instrument_status")
@Accessors(chain = true)
public final class CygInstrumentStatus {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = "instrument_code")
	private String instrumentCode;
	public static final String COLUMN_InstrumentID = "instrument_code";

	@Column(name = "trading_day")
	private long tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	@Column(name = "tradeable")
	private boolean tradeable;
	public static final String COLUMN_Tradeable = "tradeable";

}
