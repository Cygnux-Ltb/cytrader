package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.CommonColumn;
import io.cygnus.repository.constant.EntityName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * CygInfo Entity
 * 
 * @author yellow013
 * 
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_info")
@Entity(name = EntityName.CygInfo)
public final class CygInfoEntity {

	@Id
	@Column(name = "cyg_id")
	private int cygId;

	@Column(name = CommonColumn.INVESTOR_ID)
	private String investorId;

	@Column(name = CommonColumn.BROKER_ID)
	private String brokerId;

	@Column(name = "product_name", length = 128)
	private String productName;

	@Column(name = "trader_name", length = 128)
	private String traderName;

	@Column(name = "adaptor_type", length = 32)
	private String interfaceType;

}
