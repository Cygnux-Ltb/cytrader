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
// Exchange
@Entity
@Table(name = "Exchange")
@Getter
@Setter
@Accessors(chain = true)
public final class Exchange {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// ExchangeCode varchar 9
	@Column(name = "exchange_code")
	private String exchangeCode;

	// ExchangeName varchar 63
	@Column(name = "exchange_name")
	private String exchangeName;

}
