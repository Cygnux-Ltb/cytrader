package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.ColumnDefinition;
import io.cygnus.repository.constant.CommonColumn;
import io.cygnus.repository.constant.EntityName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * InstrumentSettlement Entity
 * 
 * @author yellow013
 * 
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_instrument_static")
@Entity(name = EntityName.InstrumentStatic)
public final class CygInstrumentStatic {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	/**
	 * instrumentCode
	 */
	@Column(name = CommonColumn.INSTRUMENT_CODE, nullable = false)
	private String instrumentCode;

	/**
	 * tradingDay
	 */
	@Column(name = CommonColumn.TRADING_DAY, nullable = false)
	private int tradingDay;

	/**
	 * lastPrice
	 */
	@Column(name = "last_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double lastPrice;

	/**
	 * 
	 */
	@Column(name = "open_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double openPrice;

	/**
	 * settlementPrice
	 */
	@Column(name = "settlement_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double settlementPrice;

}
