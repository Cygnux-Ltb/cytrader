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

/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "CygStrategy")
@Getter
@Setter
@Accessors(chain = true)
public final class CygStrategy {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = "cyg_id")
	private Integer cygId;
	public static final String COLUMN_NAME_CygID = "CygID";

	@Column(name = "strategy_id")
	private Integer strategyId;
	public static final String COLUMN_NAME_StrategyID = "StrategyID";

}
