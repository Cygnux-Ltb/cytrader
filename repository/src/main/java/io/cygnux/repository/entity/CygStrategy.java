package io.cygnux.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnux.repository.constant.CommonColumn;
import io.cygnux.repository.constant.EntityName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Strategy Entity
 * 
 * @author yellow013
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_strategy")
@Entity(name = EntityName.Strategy)
public final class CygStrategy {

	@Id
	@Column(name = CommonColumn.STRATEGY_ID)
	private int strategyId;

	@Column(name = CommonColumn.STRATEGY_NAME)
	private String strategyName;

	@Column(name = "strategy_owner")
	private String strategyOwner;

	@Column(name = "strategy_info")
	private String strategyInfo;

}
