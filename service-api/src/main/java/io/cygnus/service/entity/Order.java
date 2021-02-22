package io.cygnus.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.service.entity.base.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

// OrderFills
@Entity
@Table(name = "Orders")
@Getter
@Setter
@Accessors(chain = true)
public final class Order {

	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	// StrategyID int
	@Column(name = "StrategyID")
	private Integer strategyId;
	public static final String COLUMN_NAME_StrategyID = "StrategyID";

	// TradingDay date
	@Column(name = "TradingDay", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_NAME_TradingDay = "TradingDay";

	// InvestorID varchar 13
	@Column(name = "InvestorID")
	private String investorId;
	public static final String COLUMN_NAME_InvestorID = "InvestorID";

	// InstrumentID varchar 31
	@Column(name = "InstrumentID")
	private String instrumentId;
	public static final String COLUMN_NAME_InstrumentID = "InstrumentID";

	// OrderRef int
	@Column(name = "OrderRef")
	private Integer orderRef;
	public static final String COLUMN_NAME_OrderRef = "OrderRef";

	// OrderMsgType int
	@Column(name = "OrderMsgType")
	private Integer orderMsgType;
	public static final String COLUMN_NAME_OrderMsgType = "OrderMsgType";

	// UserID varchar 31
	@Column(name = "UserID")
	private String userId;
	public static final String COLUMN_NAME_UserID = "UserID";

	// Direction char
	@Column(name = "Direction")
	private char direction;
	public static final String COLUMN_NAME_Direction = "Direction";

	// Offset char
	@Column(name = "Offset")
	private char offset;
	public static final String COLUMN_NAME_Offset = "Offset";

	// LimitPrice double 19_4
	@Column(name = "LimitPrice", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double limitPrice;
	public static final String COLUMN_NAME_LimitPrice = "LimitPrice";

	// VolumeTotalOriginal int
	@Column(name = "VolumeTotalOriginal")
	private Integer volumeTotalOriginal;
	public static final String COLUMN_NAME_VolumeTotalOriginal = "VolumeTotalOriginal";

	// OrderSysID varchar 21
	@Column(name = "OrderSysID")
	private String orderSysId;
	public static final String COLUMN_NAME_OrderSysID = "OrderSysID";

	// OrderStatus char
	@Column(name = "OrderStatus")
	private char orderStatus;
	public static final String COLUMN_NAME_OrderStatus = "OrderStatus";

	@Column(name = "BrokerID")
	private String brokerId;
	public static final String COLUMN_NAME_BrokerID = "BrokerID";

	@Column(name = "OrderType")
	private Integer orderType;
	public static final String COLUMN_NAME_OrderType = "OrderType";

	@Column(name = "CounterSysID")
	private Long counterSysID;
	public static final String COLUMN_NAME_CounterSysID = "CounterSysID";

	// Volume int
	@Column(name = "Volume")
	private Integer volume;
	public static final String COLUMN_NAME_Volume = "Volume";

	// VolumeFilled int
	@Column(name = "VolumeFilled")
	private Integer volumeFilled;
	public static final String COLUMN_NAME_VolumeFilled = "VolumeFilled";

	// VolumeRemained int
	@Column(name = "VolumeRemained")
	private Integer volumeRemained;
	public static final String COLUMN_NAME_VolumeRemained = "VolumeRemained";

	// Price double 19_4
	@Column(name = "Price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double price;
	public static final String COLUMN_NAME_Price = "Price";

	// TradeID varchar 21
	@Column(name = "TradeID")
	private String tradeId;
	public static final String COLUMN_NAME_TradeID = "TradeID";

	// OrdRejReason int
	@Column(name = "OrdRejReason")
	private Integer ordRejReason;
	public static final String COLUMN_NAME_OrdRejReason = "OrdRejReason";

	// InsertTime datetime
	@Column(name = "InsertTime", columnDefinition = ColumnDefinition.TIME)
	private Date insertTime;
	public static final String COLUMN_NAME_InsertTime = "InsertTime";

	// UpdateTime datetime
	@Column(name = "UpdateTime", columnDefinition = ColumnDefinition.TIME)
	private Date updateTime;
	public static final String COLUMN_NAME_UpdateTime = "UpdateTime";

	// CancelTime datetime
	@Column(name = "CancelTime", columnDefinition = ColumnDefinition.TIME)
	private Date cancelTime;
	public static final String COLUMN_NAME_CancelTime = "CancelTime";

	// FrontID int
	@Column(name = "FrontID")
	private Integer frontId;
	public static final String COLUMN_NAME_FrontID = "FrontID";

	// SessionID int
	@Column(name = "SessionID")
	private Integer sessionId;
	public static final String COLUMN_NAME_SessionID = "SessionID";

	// StatusMsg varchar 81
	@Column(name = "StatusMsg")
	private String statusMsg;
	public static final String COLUMN_NAME_StatusMsg = "StatusMsg";

	// Fee double 19.4
	@Column(name = "Fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double fee;
	public static final String COLUMN_NAME_Fee = "Fee";

	// CounterType char
	@Column(name = "CounterType")
	private char counterType;
	public static final String COLUMN_NAME_CounterType = "CounterType";

}
