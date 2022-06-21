package io.cygnux.repository.entities.internal;

import io.cygnux.repository.constant.CommonColumn;
import io.cygnux.repository.constant.EntityName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "in_strategy")
@Entity(name = EntityName.Strategy)
public final class InStrategy {

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
