package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity(name = TableName.CYG_INFO)
public final class CygInfo {

	@Id
	@Column(name = CygInfoColumn.CYG_ID)
	private Integer cygId;

	// ProductName varchar 127
	@Column(name = CygInfoColumn.PRODUCT_NAME)
	private String productName;

	// TraderName varchar 63
	@Column(name = CygInfoColumn.TRADER_NAME)
	private String traderName;

	// InvestorID varchar 13
	@Column(name = CygInfoColumn.INVESTOR_ID)
	private String investorId;

	// BrokerID varchar 11
	@Column(name = CygInfoColumn.BROKER_ID)
	private String brokerId;

	// BinnerInterval int
	@Column(name = CygInfoColumn.BINNER_INTERVAL)
	private Integer binnerInterval;

	// ExchangeCode varchar 9
	@Column(name = CygInfoColumn.EXCHANGE_CODE)
	private String exchangeCode;

	// InterfaceMode varchar 10
	@Column(name = CygInfoColumn.INTERFACE_MODE)
	private String interfaceMode;

	// InterfaceType char
	@Column(name = CygInfoColumn.INTERFACE_TYPE)
	private int interfaceType;

	/**
	 * 
	 * @author yellow013
	 *
	 */
	public static interface CygInfoColumn {
		String CYG_ID = "cyg_id";
		String PRODUCT_NAME = "product_name";
		String TRADER_NAME = "trader_name";
		String INVESTOR_ID = "investor_id";
		String BROKER_ID = "broker_id";
		String BINNER_INTERVAL = "binner_interval";
		String EXCHANGE_CODE = "exchange_code";
		String INTERFACE_MODE = "interface_mode";
		String INTERFACE_TYPE = "interface_type";
	}

}
