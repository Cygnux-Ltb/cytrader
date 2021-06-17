package io.cygnus.service.entity;

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

// SymbolTradingFee
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "SymbolTradingFee")
@Getter
@Setter
@Accessors(chain = true)
public final class SymbolTradingFee {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// Symbol varchar 10
	@Column(name = "Symbol")
	private String symbol;
	public static final String COLUMN_NAME_Symbol = "Symbol";

	// FeeType char
	@Column(name = "FeeType")
	private String feeType;
	public static final String COLUMN_NAME_FeeType = "FeeType";

	// Fee double 19_4
	@Column(name = "Fee", columnDefinition = ColumnDefinition.DECIMAL_19_8)
	private double fee;
	public static final String COLUMN_NAME_Fee = "Fee";

	// FeeFormat char
	@Column(name = "FeeFormat")
	private String feeFormat;
	public static final String COLUMN_NAME_FeeFormat = "FeeFormat";

}
