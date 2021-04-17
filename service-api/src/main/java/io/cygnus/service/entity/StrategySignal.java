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

/**
 * StrategySymbol
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "StrategySignal")
@Getter
@Setter
@Accessors(chain = true)
public final class StrategySignal {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static final String COLUMN_NAME_StrategyID = "StrategyID";

	// SignalID int
	@Column(name = "SignalID")
	private Integer signalID;
	public static final String COLUMN_NAME_SignalID = "SignalID";

	// LimitPrice double 19_4
	@Column(name = "SignalWeight", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double signalWeight;
	public static final String COLUMN_NAME_SignalWeight = "SignalWeight";

}
