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

// StrategyParamFields
@Entity
@Table(name = "StrategyParamFields")
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyParamFields  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	// Name varchar 63
	@Column(name = "Name")
	private String name;

	// Type char
	@Column(name = "Type")
	private String type;


}
