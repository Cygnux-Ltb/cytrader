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

// SymbolInfo
@Entity
@Table(name = "SymbolInfo")
@Getter
@Setter
@Accessors(chain = true)
public final class SymbolInfo {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// Symbol varchar 10
	@Column(name = "Symbol")
	private String symbol;
	public static final String COLUMN_NAME_Symbol = "Symbol";

	// ExchangeCode varchar 9
	@Column(name = "ExchangeCode")
	private String exchangeCode;
	public static final String COLUMN_NAME_ExchangeCode = "ExchangeCode";

	// Multiplier int
	@Column(name = "Multiplier")
	private Integer multiplier;
	public static final String COLUMN_NAME_Multiplier = "Multiplier";

	// TickSize double 19_4
	@Column(name = "TickSize", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double tickSize;
	public static final String COLUMN_NAME_TickSize = "TickSize";

	// Margin double 19_4
	@Column(name = "Margin", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double margin;
	public static final String COLUMN_NAME_Margin = "Margin";

	// Type varchar 9
	@Column(name = "Type")
	private String type;
	public static final String COLUMN_NAME_Type = "Type";

}
