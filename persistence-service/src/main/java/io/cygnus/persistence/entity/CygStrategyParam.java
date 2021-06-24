package io.cygnus.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

// StrategyParam
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "cyg_strategy_param")
@Getter
@Setter
@Accessors(chain = true)
public final class CygStrategyParam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private long uid;

	// StrategyID int
	@Column(name = "strategy_id")
	private int strategyId;
	public static final String COLUMN_StrategyID = "strategy_id";

	// Name varchar 63
	@Column(name = "param_name")
	private String paramName;
	public static final String COLUMN_ParamName = "Name";

	// ValueInt int
	@Column(name = "param_value")
	private String paramValue;
	public static final String COLUMN_ParamValue = "param_value";

	// ValueType char
	@Column(name = "param_value_type")
	private String paramValueType;
	public static final String COLUMN_ParamValueType = "param_value_type";

}
