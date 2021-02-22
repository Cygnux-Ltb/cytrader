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
@Table(name = "SignalSymbol")
@Getter
@Setter
@Accessors(chain = true)
public final class SignalSymbol {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// SignalID int
	@Column(name = "SignalID")
	private Integer signalID;
	public static final String COLUMN_NAME_SignalID = "SignalID";

	// StrategySymbol varchar 10
	@Column(name = "StrategySymbol")
	private String strategySymbol;
	public static final String COLUMN_NAME_StrategySymbol = "StrategySymbol";

	// ReferenceSymbol varchar 10
	@Column(name = "ReferenceSymbol")
	private String referenceSymbol;
	public static final String COLUMN_NAME_ReferenceSymbol = "ReferenceSymbol";

}
