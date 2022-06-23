package io.cygnux.repository.entities;

import io.cygnux.repository.constant.CommonColumn;
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
@Table(name = "strategy")
@Entity(name = ItStrategy.EntityName)
public final class ItStrategy {

	public final static String EntityName = "ItStrategy";

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
