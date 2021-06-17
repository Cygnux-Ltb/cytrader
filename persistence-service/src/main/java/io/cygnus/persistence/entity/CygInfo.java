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
@Table(name = "CygInfo")
@Getter
@Setter
@Accessors(chain = true)
public final class CygInfo {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// CygID int
	@Column(name = "cyg_id")
	private Integer cygId;
	public static final String COLUMN_NAME_CygID = "CygID";

	// ProductName varchar 127
	@Column(name = "product_name")
	private String productName;
	public static final String COLUMN_NAME_ProductName = "ProductName";

	// TraderName varchar 63
	@Column(name = "trader_name")
	private String traderName;
	public static final String COLUMN_NAME_TraderName = "TraderName";

	// InvestorID varchar 13
	@Column(name = "investor_id")
	private String investorId;
	public static final String COLUMN_NAME_InvestorID = "InvestorID";

	// BrokerID varchar 11
	@Column(name = "broker_id")
	private String brokerId;
	public static final String COLUMN_NAME_BrokerID = "BrokerID";

	// BinnerInterval int
	@Column(name = "binner_interval")
	private Integer binnerInterval;
	public static final String COLUMN_NAME_BinnerInterval = "BinnerInterval";

	// InterfaceMode varchar 10
	@Column(name = "interface_mode")
	private String interfaceMode;
	public static final String COLUMN_NAME_InterfaceMode = "InterfaceMode";

	// ExchangeCode varchar 9
	@Column(name = "exchange_code")
	private String exchangeCode;
	public static final String COLUMN_NAME_ExchangeCode = "ExchangeCode";

	// InterfaceType char
	@Column(name = "interface_type")
	private char interfaceType;
	public static final String COLUMN_NAME_InterfaceType = "InterfaceType";

}
