package io.cygnus.persistence.entity;

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
 *         CygInfo
 *
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "cyg_info")
@Table(name = "cyg_info")
public final class CygInfo {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// CygID int
	@Column(name = "cyg_id")
	private Integer cygId;
	public static final String COLUMN_CygID = "cyg_id";

	// ProductName varchar 127
	@Column(name = "product_name")
	private String productName;
	public static final String COLUMN_ProductName = "product_name";

	// TraderName varchar 63
	@Column(name = "trader_name")
	private String traderName;
	public static final String COLUMN_TraderName = "trader_name";

	// InvestorID varchar 13
	@Column(name = "investor_id")
	private String investorId;
	public static final String COLUMN_InvestorID = "investor_id";

	// BrokerID varchar 11
	@Column(name = "broker_id")
	private String brokerId;
	public static final String COLUMN_BrokerID = "broker_id";

	// BinnerInterval int
	@Column(name = "binner_interval")
	private Integer binnerInterval;
	public static final String COLUMN_BinnerInterval = "binner_interval";

	// ExchangeCode varchar 9
	@Column(name = "exchange_code")
	private String exchangeCode;
	public static final String COLUMN_ExchangeCode = "exchange_code";

	// InterfaceMode varchar 10
	@Column(name = "interface_mode")
	private String interfaceMode;
	public static final String COLUMN_InterfaceMode = "interface_mode";

	// InterfaceType char
	@Column(name = "interface_type")
	private int interfaceType;
	public static final String COLUMN_InterfaceType = "interface_type";

}
