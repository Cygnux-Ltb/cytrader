package io.cygnux.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnux.repository.constant.CommonColumn;
import io.cygnux.repository.constant.EntityName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * StrategyParam Entity
 * 
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_strategy_param")
@Entity(name = EntityName.StrategyParam)
public final class CygStrategyParam {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	@Column(name = CommonColumn.STRATEGY_ID)
	private int strategyId;

	@Column(name = CommonColumn.STRATEGY_NAME)
	private String strategyName;

	@Column(name = "param_name")
	private String paramName;

	@Column(name = "param_type")
	private String paramType;

	@Column(name = "param_value")
	private String paramValue;

}
