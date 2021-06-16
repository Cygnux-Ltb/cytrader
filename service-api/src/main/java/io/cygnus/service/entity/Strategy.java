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

// Strategy
/**
 * 
 * @author yellow013
 *
 */
@Entity
@Table(name = "Strategy")
@Getter
@Setter
@Accessors(chain = true)
public final class Strategy {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static final String COLUMN_StrategyID = "StrategyID";

	// StrategyName varchar 15
	@Column(name = "StrategyName")
	private String strategyName;
	public static final String COLUMN_StrategyName = "StrategyName";

}
