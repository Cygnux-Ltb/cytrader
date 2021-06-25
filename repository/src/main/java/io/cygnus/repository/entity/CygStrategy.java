package io.cygnus.repository.entity;

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
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_strategy")
@Entity(name = "cyg_strategy")
public final class CygStrategy {

	@Column(name = "strategy_id")
	private int strategyId;
	public static final String COLUMN_StrategyID = "strategy_id";
	
	@Column(name = "strategy_name")
	private int strategyName;
	public static final String COLUMN_StrategyName = "strategy_name";

}
