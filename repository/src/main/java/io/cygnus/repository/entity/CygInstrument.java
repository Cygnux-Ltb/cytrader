package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.CommonQueryColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygInstrument
 *
 */

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_instrument")
@Entity(name = "cyg_instrument")
public final class CygInstrument {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = CommonQueryColumn.INSTRUMENT_CODE)
	private String instrumentCode;
	public static final String COLUMN_InstrumentID = "instrument_code";

	@Column(name = CommonQueryColumn.TRADING_DAY)
	private int tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	@Column(name = "fee")
	private long fee;

	@Column(name = "tradeable")
	private boolean tradeable;

}
