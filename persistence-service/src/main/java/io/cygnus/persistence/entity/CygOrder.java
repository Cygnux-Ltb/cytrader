package io.cygnus.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.persistence.entity.base.ColumnDefinition;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author yellow013
 * 
 *         CygOrder
 *
 */
@Data
@Entity
@Table(name = "cyg_order")
@Accessors(chain = true)
public final class CygOrder {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	// StrategyID int
	@Column(name = "strategy_id")
	private int strategyId;
	public static final String COLUMN_StrategyID = "strategy_id";

	// TradingDay date
	@Column(name = "trading_day", columnDefinition = ColumnDefinition.DATE)
	private Date tradingDay;
	public static final String COLUMN_TradingDay = "trading_day";

	// InvestorID varchar 13
	@Column(name = "investor_id")
	private String investorId;
	public static final String COLUMN_InvestorID = "investor_id";

	// InstrumentID varchar 31
	@Column(name = "instrument_code")
	private String instrumentCode;
	public static final String COLUMN_InstrumentCode = "instrument_code";

	// OrderRef int
	@Column(name = "order_ref")
	private Integer orderRef;
	public static final String COLUMN_OrderRef = "OrderRef";

	// OrderMsgType int
	@Column(name = "order_msg_type")
	private Integer orderMsgType;
	public static final String COLUMN_OrderMsgType = "OrderMsgType";

	// UserID varchar 31
	@Column(name = "user_id")
	private String userId;
	public static final String COLUMN_UserID = "UserID";

	// Direction char
	@Column(name = "direction")
	private char direction;
	public static final String COLUMN_Direction = "Direction";

	// Offset char
	@Column(name = "offset")
	private char offset;
	public static final String COLUMN_Offset = "Offset";

	// LimitPrice double 19_4
	@Column(name = "limit_price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double limitPrice;
	public static final String COLUMN_LimitPrice = "LimitPrice";

	// VolumeTotalOriginal int
	@Column(name = "volume_total_original")
	private Integer volumeTotalOriginal;
	public static final String COLUMN_VolumeTotalOriginal = "VolumeTotalOriginal";

	// OrderSysID varchar 21
	@Column(name = "order_sys_id")
	private String orderSysId;
	public static final String COLUMN_OrderSysID = "OrderSysID";

	// OrderStatus char
	@Column(name = "order_status")
	private char orderStatus;
	public static final String COLUMN_OrderStatus = "OrderStatus";

	@Column(name = "broker_id")
	private String brokerId;
	public static final String COLUMN_BrokerID = "BrokerID";

	@Column(name = "order_type")
	private Integer orderType;
	public static final String COLUMN_OrderType = "OrderType";

	@Column(name = "counter_sys_id")
	private Long counterSysID;
	public static final String COLUMN_CounterSysID = "CounterSysID";

	// Volume int
	@Column(name = "volume")
	private Integer volume;
	public static final String COLUMN_Volume = "Volume";

	// VolumeFilled int
	@Column(name = "volume_filled")
	private Integer volumeFilled;
	public static final String COLUMN_VolumeFilled = "VolumeFilled";

	// VolumeRemained int
	@Column(name = "volume_remained")
	private Integer volumeRemained;
	public static final String COLUMN_VolumeRemained = "VolumeRemained";

	// Price double 19_4
	@Column(name = "price", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double price;
	public static final String COLUMN_Price = "Price";

	// TradeID varchar 21
	@Column(name = "tradeID")
	private String tradeId;
	public static final String COLUMN_TradeID = "TradeID";

	// OrdRejReason int
	@Column(name = "ord_rej_reason")
	private Integer ordRejReason;
	public static final String COLUMN_OrdRejReason = "OrdRejReason";

	// InsertTime datetime
	@Column(name = "insert_time", columnDefinition = ColumnDefinition.TIME)
	private Date insertTime;
	public static final String COLUMN_InsertTime = "InsertTime";

	// UpdateTime datetime
	@Column(name = "update_time", columnDefinition = ColumnDefinition.TIME)
	private Date updateTime;
	public static final String COLUMN_UpdateTime = "UpdateTime";

	// CancelTime datetime
	@Column(name = "cancel_time", columnDefinition = ColumnDefinition.TIME)
	private Date cancelTime;
	public static final String COLUMN_CancelTime = "CancelTime";

	// FrontID int
	@Column(name = "front_id")
	private Integer frontId;
	public static final String COLUMN_FrontID = "FrontID";

	// SessionID int
	@Column(name = "session_id")
	private Integer sessionId;
	public static final String COLUMN_SessionID = "SessionID";

	// StatusMsg varchar 81
	@Column(name = "status_msg")
	private String statusMsg;
	public static final String COLUMN_StatusMsg = "StatusMsg";

	// Fee double 19.4
	@Column(name = "fee", columnDefinition = ColumnDefinition.DECIMAL_19_4)
	private double fee;
	public static final String COLUMN_Fee = "Fee";

	// CounterType char
	@Column(name = "counter_type")
	private char counterType;
	public static final String COLUMN_CounterType = "CounterType";

}
