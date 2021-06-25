package io.cygnus.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "cyg_strategy")
@Getter
@Setter
@Accessors(chain = true)
public final class CygStrategy {

	@Column(name = "strategy_id")
	private int strategyId;
	public static final String COLUMN_StrategyID = "strategy_id";
	
	@Column(name = "strategy_name")
	private int strategyName;
	public static final String COLUMN_StrategyName = "strategy_name";

}
