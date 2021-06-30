package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.CommonQueryColumn;
import io.cygnus.repository.constant.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygInfo
 *
 */

@Getter
@Setter
@Accessors(chain = true)
@Table(name = TableName.CYG_INFO)
@Entity(name = "CygInfo")
public final class CygInfo {

	@Id
	@Column(name = CygInfoQueryColumn.CYG_ID)
	private Integer cygId;

	@Column(name = CommonQueryColumn.INVESTOR_ID)
	private String investorId;

	@Column(name = CommonQueryColumn.BROKER_ID)
	private String brokerId;

	@Column(name = "product_name", length = 127)
	private String productName;

	@Column(name = "trader_name", length = 127)
	private String traderName;

	@Column(name = "adaptor_type", length = 16)
	private String interfaceType;

	/**
	 * 
	 * @author yellow013
	 *
	 */
	public static interface CygInfoQueryColumn {

		String CYG_ID = "cyg_id";
		
	}

}
