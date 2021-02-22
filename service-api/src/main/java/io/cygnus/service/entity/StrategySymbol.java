package io.cygnus.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

//StrategySymbol
@Entity
@Table(name = "StrategySymbol")
@Getter
@Setter
@Accessors(chain = true)
public final class StrategySymbol {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static final String COLUMN_NAME_StrategyID = "StrategyID";

	// Symbol varchar 10
	@Column(name = "Symbol")
	private String symbol;
	public static final String COLUMN_NAME_Symbol = "Symbol";

}
