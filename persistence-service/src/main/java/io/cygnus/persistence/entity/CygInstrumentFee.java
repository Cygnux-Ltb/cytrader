package io.cygnus.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygInstrumentFee
 *
 */
@Data
@Entity
@Table(name = "cyg_instrument_fee")
@Accessors(chain = true)
public final class CygInstrumentFee {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// Symbol varchar 10
	@Column(name = "symbol")
	private String symbol;
	public static final String COLUMN_Symbol = "Symbol";

	// FeeType char
	@Column(name = "fee_type")
	private String feeType;
	public static final String COLUMN_FeeType = "FeeType";

	// Fee double 19_4
	@Column(name = "fee")
	private long fee;
	public static final String COLUMN_Fee = "Fee";

}
